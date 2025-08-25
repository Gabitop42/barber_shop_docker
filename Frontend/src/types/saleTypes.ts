export interface Sale {
  id: number
  fecha: string
  idUsuario: number
  total: number
  detalles: Detalle[]
}

export interface Detalle {
  id: number
  idServicioProducto: number
  cantidad: number
  precioUnitario: number
  subtotal: number
}
