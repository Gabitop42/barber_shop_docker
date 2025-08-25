export interface Service {
  id?: number
  nombre: string
  descripcion: string
  precioBase: number
  porcentajeComision: number
  activo: boolean
  tipo: "SERVICIO" | "PRODUCTO"
}