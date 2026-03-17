variable "location" {
  type    = string
  default = "Norway East"
}

variable "admin_password" {
  description = "Contraseña del usuario admin"
  type        = string
  default     = "P@ssword123!"
}

variable "vm_size" {
  description = "Tamaño de la máquina virtual"
  type        = string
  default     = "Standard_B2ats_v2"
}

variable "subnet_address_prefix" {
  description = "Prefijo de direcciones de la subred"
  type        = string
  default     = "10.0.1.0/24"
}

variable "resource_group_name" {
  description = "PruebaTerraform"
  type        = string
  default     = "testjg-rg"
}
variable "public_ip" {
  type    = string
  default = ""
}

variable "my_local_ip" {
  type    = string
  default = "81.40.251.122"

}
variable "ssh_public_key" {
  description = "Clave pública SSH"
  type        = string
  
}
variable "node_size" {
  description = "Tamaño de la máquina virtual"
  type        = string
  default     = "Standard_D2s_v3"
}

variable "locationCluster" {
  type    = string
  default = "Spain Central"
}
variable "postgres_admin_user" {
  description = "Usuario administrador de PostgreSQL"
  type        = string
  default     = "adminaz"
}

variable "postgres_admin_password" {
  description = "Contraseña del usuario administrador de PostgreSQL"
  type        = string
  default     = "Rafael27"
  sensitive   = true
}