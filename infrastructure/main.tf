# --------------------------
# Resource Group
# --------------------------
resource "azurerm_resource_group" "rg" {
  name     = var.resource_group_name
  location = var.location
}

# --------------------------
# Virtual Network
# --------------------------
resource "azurerm_virtual_network" "vnet" {
  name                = "vnetjg"
  address_space       = ["10.0.0.0/16"]
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
}

# --------------------------
# Subnet
# --------------------------
resource "azurerm_subnet" "subnet" {
  name                 = "subnetjg"
  resource_group_name  = azurerm_resource_group.rg.name
  virtual_network_name = azurerm_virtual_network.vnet.name
  address_prefixes     = ["10.0.1.0/24"]
}

# --------------------------
# Public IP + NIC
# --------------------------
resource "azurerm_public_ip" "pip" {
  name                = "publicipjg"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name
  allocation_method   = "Static"
  sku                 = "Standard"
}

resource "azurerm_network_interface" "nic" {
  name                = "nicjg"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name

  ip_configuration {
    name                          = "internal"
    subnet_id                     = azurerm_subnet.subnet.id
    private_ip_address_allocation = "Dynamic"
    public_ip_address_id          = azurerm_public_ip.pip.id
  }
}

# --------------------------
# NSG
# --------------------------
resource "azurerm_network_security_group" "nsg" {
  name                = "nsg-jg"
  location            = var.location
  resource_group_name = azurerm_resource_group.rg.name

  security_rule {
    name                       = "Allow-SSH"
    priority                   = 1001
    direction                  = "Inbound"
    access                     = "Allow"
    protocol                   = "Tcp"
    source_port_range          = "*"
    destination_port_range     = "22"
    source_address_prefix      = "*"
    destination_address_prefix = "*"
  }
}

resource "azurerm_network_interface_security_group_association" "nic_nsg" {
  network_interface_id      = azurerm_network_interface.nic.id
  network_security_group_id = azurerm_network_security_group.nsg.id
}

# --------------------------
# VM Linux
# --------------------------
resource "azurerm_linux_virtual_machine" "vm" {
  name                  = "ubuntu-jg-vm"
  resource_group_name   = azurerm_resource_group.rg.name
  location              = var.location
  size                  = var.vm_size
  network_interface_ids = [azurerm_network_interface.nic.id]
  admin_username        = "azureuser"

  os_disk {
    name                 = "osdisk-ubuntu"
    caching              = "ReadWrite"
    storage_account_type = "Standard_LRS"
  }

  source_image_reference {
    publisher = "Canonical"
    offer     = "0001-com-ubuntu-server-jammy"
    sku       = "22_04-lts"
    version   = "latest"
  }

  admin_ssh_key {
    username   = "azureuser"
    public_key = var.ssh_public_key
  }

  disable_password_authentication = true
}

# --------------------------
# Azure Container Registry
# --------------------------
resource "azurerm_container_registry" "acr" {
  name                = "contenedoresjg12345"
  resource_group_name = azurerm_resource_group.rg.name
  location            = var.location
  sku                 = "Basic"
  admin_enabled       = false
}

# --------------------------
# AKS Cluster
# --------------------------
resource "azurerm_kubernetes_cluster" "aks" {
  name                = "aks-cluster-jg"
  location            = var.locationCluster
  resource_group_name = azurerm_resource_group.rg.name
  dns_prefix          = "aks-cluster-jg"

  kubernetes_version = "1.33.5"

  default_node_pool {
    name       = "default"
    node_count = 2
    vm_size    = var.node_size
  }

  identity {
    type = "SystemAssigned"
  }
}

resource "azurerm_role_assignment" "aks_acr_pull" {
  scope                = azurerm_container_registry.acr.id
  role_definition_name = "AcrPull"
  principal_id         = azurerm_kubernetes_cluster.aks.kubelet_identity[0].object_id
}

# --------------------------
# PostgreSQL Público
# --------------------------
resource "azurerm_postgresql_flexible_server" "postgres" {
  name                   = "postgres-testjg"
  resource_group_name    = azurerm_resource_group.rg.name
  location               = var.location
  version                = "15"
  sku_name               = "B_Standard_B1ms"

  administrator_login    = var.postgres_admin_user
  administrator_password = var.postgres_admin_password

  storage_mb            = 32768
  backup_retention_days = 7
}

resource "azurerm_postgresql_flexible_server_firewall_rule" "allow_my_ip" {
  name             = "allow-my-ip"
  server_id        = azurerm_postgresql_flexible_server.postgres.id
  start_ip_address = "0.0.0.0"
  end_ip_address   = "255.255.255.255"
}