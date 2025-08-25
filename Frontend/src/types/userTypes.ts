export interface User {
  idUsuario?: number
  email: string
  nombre: string
  apellido: string
  telefono: string
  estado: boolean
  rol: "BARBERO" | "ADMIN"
}