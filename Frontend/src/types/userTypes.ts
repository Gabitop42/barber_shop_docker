export interface User {
  idUsuario?: number
  email: string
  nombre: string
  apellido: string
  telefono: string
  estado: boolean
  rol: "BARBERO" | "ADMIN"
}

export interface UserAttend {
  idUsuario: number
  nombre: string
  apellido: string
}
