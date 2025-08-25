import Plus from "@/assets/icons/plus"
import User from "@/assets/icons/users"
import Package from "@/assets/icons/product"
import Minus from "@/assets/icons/minus"
import X from "@/assets/icons/x"
import { useRef, useState } from "react"

const mockBarberos = [
  { id: 1, nombre: "Carlos", apellido: "Mendoza" },
  { id: 2, nombre: "Miguel", apellido: "Torres" },
  { id: 3, nombre: "José", apellido: "Condori" },
]

const mockServicios = [
  {
    id: 1,
    nombre: "Corte Clásico",
    precioBase: 50.0,
    tipo: "SERVICIO",
    porcentajeComision: 15,
  },
  {
    id: 2,
    nombre: "Corte + Barba",
    precioBase: 80.0,
    tipo: "SERVICIO",
    porcentajeComision: 20,
  },
  {
    id: 3,
    nombre: "Pomada Premium",
    precioBase: 35.0,
    tipo: "PRODUCTO",
    porcentajeComision: 10,
  },
  {
    id: 4,
    nombre: "Aceite de Barba",
    precioBase: 40.0,
    tipo: "PRODUCTO",
    porcentajeComision: 12,
  },
  {
    id: 5,
    nombre: "Champú Especial",
    precioBase: 25.0,
    tipo: "PRODUCTO",
    porcentajeComision: 8,
  },
]

export const CreateSaleModal = () => {
  const [formData, setFormData] = useState({
    idUsuario: "",
    cliente: "",
  })
  const [detalles, setDetalles] = useState<
    Array<{
      idServicioProducto: number
      cantidad: number
      servicio?: any
    }>
  >([])
  const [selectedServicio, setSelectedServicio] = useState("")
  const dialogRef = useRef<HTMLDialogElement>(null)

  const openModal = () => {
    dialogRef.current?.showModal()
  }

  const closeModal = () => {
    dialogRef.current?.close()
  }

  const addServicio = () => {
    if (!selectedServicio) return

    const servicio = mockServicios.find(
      (s) => s.id === Number.parseInt(selectedServicio)
    )
    if (!servicio) return

    const existingIndex = detalles.findIndex(
      (d) => d.idServicioProducto === servicio.id
    )

    if (existingIndex >= 0) {
      const newDetalles = [...detalles]
      newDetalles[existingIndex].cantidad += 1
      setDetalles(newDetalles)
    } else {
      setDetalles([
        ...detalles,
        {
          idServicioProducto: servicio.id,
          cantidad: 1,
          servicio,
        },
      ])
    }

    setSelectedServicio("")
  }

  const updateCantidad = (index: number, cantidad: number) => {
    if (cantidad <= 0) {
      removeDetalle(index)
      return
    }

    const newDetalles = [...detalles]
    newDetalles[index].cantidad = cantidad
    setDetalles(newDetalles)
  }

  const removeDetalle = (index: number) => {
    setDetalles(detalles.filter((_, i) => i !== index))
  }

  const calculateTotal = () => {
    return detalles.reduce((total, detalle) => {
      return total + (detalle.servicio?.precioBase || 0) * detalle.cantidad
    }, 0)
  }

  const calculateComisiones = () => {
    return detalles.reduce((total, detalle) => {
      const subtotal = (detalle.servicio?.precioBase || 0) * detalle.cantidad
      const comision =
        subtotal * ((detalle.servicio?.porcentajeComision || 0) / 100)
      return total + comision
    }, 0)
  }

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
  }

  return (
    <>
      <button
        onClick={openModal}
        className="bg-burdeos hover:bg-burdeos/90 text-marfil flex items-center px-4 py-2 rounded-lg text-sm font-medium transition-colors"
      >
        <Plus className="h-4 w-4 mr-2" />
        Nueva venta
      </button>

      <dialog
        ref={dialogRef}
        className="bg-carbon m-auto backdrop:bg-black/40 border-marfil/20 max-w-lg p-6 rounded-lg w-full border"
      >
        <h3 className="text-marfil flex items-center justify-between text-lg font-medium mb-1">
          Nuevo Usuario
        </h3>
        <p className="text-marfil/70 text-sm mb-6">
          Completa los datos para crear un nuevo usuario
        </p>

        <form onSubmit={handleSubmit} className="space-y-6">
          {/* Información básica */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div className="space-y-2">
              <label htmlFor="barbero" className="text-marfil">
                Barbero *
              </label>
              <select
                value={formData.idUsuario}
                // onValueChange={(value) =>
                //   setFormData({ ...formData, idUsuario: value })
                // }
                required
              >
                {mockBarberos.map((barbero) => (
                  <option
                    key={barbero.id}
                    value={barbero.id.toString()}
                    className="text-marfil hover:bg-burdeos/20"
                  >
                    {barbero.nombre} {barbero.apellido}
                  </option>
                ))}
              </select>
            </div>

            <div className="space-y-2">
              <label htmlFor="cliente" className="text-marfil">
                Cliente
              </label>
              <input
                id="cliente"
                value={formData.cliente}
                onChange={(e) =>
                  setFormData({ ...formData, cliente: e.target.value })
                }
                placeholder="Nombre del cliente (opcional)"
                className="bg-carbon border-marfil/30 text-marfil placeholder:text-marfil/50"
              />
            </div>
          </div>

          {/* Agregar servicios/productos */}
          <div className="bg-carbon/50 border-marfil/20">
            <h3 className="text-marfil text-lg">Servicios y Productos</h3>
            <div className="space-y-4">
              <div className="flex gap-2">
                <select
                // value={selectedServicio}
                // onValueChange={setSelectedServicio}
                >
                  <div className="bg-carbon border-marfil/30">
                    {mockServicios.map((servicio) => (
                      <option
                        key={servicio.id}
                        value={servicio.id.toString()}
                        className="text-marfil hover:bg-burdeos/20"
                      >
                        <div className="flex items-center justify-between w-full">
                          <span>{servicio.nombre}</span>
                          <div className="flex items-center gap-2 ml-4">
                            <div className="text-xs">{servicio.tipo}</div>
                            <span className="text-burdeos font-medium">
                              S/ {servicio.precioBase}
                            </span>
                          </div>
                        </div>
                      </option>
                    ))}
                  </div>
                </select>
                <button
                  type="button"
                  onClick={addServicio}
                  disabled={!selectedServicio}
                  className="bg-burdeos hover:bg-burdeos/90 text-marfil"
                >
                  <Plus className="w-4 h-4" />
                </button>
              </div>

              {/* Lista de servicios agregados */}
              {detalles.length > 0 && (
                <div className="space-y-2">
                  {detalles.map((detalle, index) => (
                    <div
                      key={index}
                      className="flex items-center justify-between p-3 bg-carbon/30 rounded-lg border border-marfil/10"
                    >
                      <div className="flex items-center gap-3">
                        {detalle.servicio?.tipo === "SERVICIO" ? (
                          <User className="w-4 h-4 text-burdeos" />
                        ) : (
                          <Package className="w-4 h-4 text-burdeos" />
                        )}
                        <div>
                          <div className="text-marfil font-medium">
                            {detalle.servicio?.nombre}
                          </div>
                          <div className="text-marfil/60 text-sm">
                            S/ {detalle.servicio?.precioBase} c/u
                          </div>
                        </div>
                      </div>

                      <div className="flex items-center gap-2">
                        <button
                          type="button"
                          onClick={() =>
                            updateCantidad(index, detalle.cantidad - 1)
                          }
                          className="text-marfil hover:bg-burdeos/20"
                        >
                          <Minus className="w-3 h-3" />
                        </button>

                        <span className="text-marfil font-medium w-8 text-center">
                          {detalle.cantidad}
                        </span>

                        <button
                          type="button"
                          // variant="ghost"
                          // size="sm"
                          onClick={() =>
                            updateCantidad(index, detalle.cantidad + 1)
                          }
                          className="text-marfil hover:bg-burdeos/20"
                        >
                          <Plus className="w-3 h-3" />
                        </button>

                        <div className="text-marfil font-semibold ml-4 min-w-[80px] text-right">
                          S/{" "}
                          {(
                            (detalle.servicio?.precioBase || 0) *
                            detalle.cantidad
                          ).toFixed(2)}
                        </div>

                        <button
                          type="button"
                          onClick={() => removeDetalle(index)}
                          className="text-red-400 hover:text-red-300 hover:bg-red-500/20 ml-2"
                        >
                          <X className="w-4 h-4" />
                        </button>
                      </div>
                    </div>
                  ))}
                </div>
              )}
            </div>
          </div>

          {/* Resumen */}
          {detalles.length > 0 && (
            <div className="bg-burdeos/10 border-burdeos/30">
              <div className="pt-6">
                <div className="space-y-2">
                  <div className="flex justify-between text-marfil">
                    <span>Subtotal:</span>
                    <span>S/ {calculateTotal().toFixed(2)}</span>
                  </div>
                  <div className="flex justify-between text-marfil/70 text-sm">
                    <span>Comisiones totales:</span>
                    <span>S/ {calculateComisiones().toFixed(2)}</span>
                  </div>
                  <div className="border-t border-marfil/20 pt-2">
                    <div className="flex justify-between text-marfil font-bold text-lg">
                      <span>Total:</span>
                      <span>S/ {calculateTotal().toFixed(2)}</span>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* Botones */}
          <div className="flex justify-end gap-3 pt-4">
            <button
              type="button"
              onClick={closeModal}
              className="border-marfil/30 text-marfil hover:bg-marfil/10 bg-transparent"
            >
              Cancelar
            </button>
            <button
              type="submit"
              disabled={!formData.idUsuario || detalles.length === 0}
              className="bg-burdeos hover:bg-burdeos/90 text-marfil"
            >
              Registrar Venta
            </button>
          </div>
        </form>
      </dialog>
    </>
  )
}
