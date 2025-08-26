import Plus from "@/assets/icons/plus"
import { useRef, useState } from "react"
import { Switch } from "./Switch"
import { formToJSON } from "@/utils/formToJSON"

type ServerError = { message?: string }

export const CreateServiceModal = () => {
  const [submitting, setSubmitting] = useState(false)
  const [serverError, setServerError] = useState<string | null>(null)
  const dialogRef = useRef<HTMLDialogElement>(null)

  const openModal = () => {
    dialogRef.current?.showModal()
  }

  const closeModal = () => {
    dialogRef.current?.close()
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setServerError(null)

    const form = e.currentTarget
    const data = formToJSON(form)

    if (
      !data.nombre ||
      !data.descripcion ||
      !data.precioBase ||
      !data.porcentajeComision ||
      !data.tipo
    ) {
      setServerError("Completa todos los campos obligatorios.")
      return
    }

    console.log(JSON.stringify(data))
    try {
      setSubmitting(true)
      const res = await fetch("http://localhost:9090/api/servicio-producto", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      })

      if (!res.ok) {
        const err: ServerError = await res
          .json()
          .catch(() => ({ message: `HTTP ${res.status}` }))
        throw new Error(err?.message || "No se pudo crear el servicio")
      }

      form.reset()
      closeModal()
      window.location.reload()
    } catch (err: any) {
      setServerError(err.message || "Error inesperado al crear el servicio")
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <>
      <button
        onClick={openModal}
        className="bg-burdeos hover:bg-burdeos/90 text-marfil flex items-center px-4 py-2 rounded-lg text-sm font-medium transition-colors"
      >
        <Plus className="h-4 w-4 mr-2" />
        Nuevo Servicio
      </button>

      <dialog
        ref={dialogRef}
        className="bg-carbon m-auto backdrop:bg-black/40 border-marfil/20 max-w-lg p-6 rounded-lg w-full border"
      >
        <h3 className="text-marfil flex items-center justify-between text-lg font-medium mb-1">
          Crear Servicio
        </h3>
        <p className="text-marfil/70 text-sm mb-4">
          Completa los datos para crear un nuevo servicio o producto.
        </p>

        <form onSubmit={handleSubmit} className="space-y-2">
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-1.5">
              <label htmlFor="tipo" className="text-marfil block">
                Tipo
              </label>
              <select
                name="tipo"
                id="tipo"
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 rounded-md"
              >
                <option
                  value="SERVICIO"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Servicio
                </option>
                <option
                  value="PRODUCTO"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Producto
                </option>
              </select>
            </div>
            <div className="space-y-1.5">
              <label htmlFor="activo" className="text-marfil block">
                Estado Activo
              </label>
              <Switch
                name="activo"
                id="activo"
                defaultChecked
                theme="burdeos"
                switchSize="md"
              />
            </div>
          </div>

          <div className="space-y-1.5">
            <label htmlFor="nombre" className="text-marfil block">
              Nombre
            </label>
            <input
              id="nombre"
              name="nombre"
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="Ingresa el nombre"
            />
          </div>

          <div className="space-y-1.5">
            <label htmlFor="descripcion" className="text-marfil block">
              Descripción
            </label>
            <textarea
              name="descripcion"
              id="descripcion"
              placeholder="Describe el servicio o producto"
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
            ></textarea>
          </div>

          <div className="space-y-1.5">
            <label htmlFor="precioBase" className="text-marfil block">
              Precio Base (S/)
            </label>
            <input
              name="precioBase"
              id="precioBase"
              type="number"
              step="0.01"
              min="0"
              className="bg-carbon/50 border-marfil/20 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="0.00"
            />
          </div>

          <div className="space-y-1.5">
            <label htmlFor="porcentajeComision" className="text-marfil block">
              Porcentaje de Comisión (%)
            </label>
            <input
              name="porcentajeComision"
              id="porcentajeComision"
              type="number"
              step="0.01"
              min="0"
              max="100"
              className="bg-carbon/50 border-marfil/20 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="0.00"
            />
          </div>

          <div className="flex justify-end gap-3 pt-4">
            <button
              onClick={closeModal}
              type="button"
              className="bg-marfil/10 text-marfil border-marfil/20 hover:bg-marfil/20 py-1 px-2 rounded-md"
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="bg-burdeos hover:bg-burdeos/90 text-marfil py-1 px-2 rounded-md"
            >
              Crear Servicio
            </button>
          </div>
        </form>
      </dialog>
    </>
  )
}
