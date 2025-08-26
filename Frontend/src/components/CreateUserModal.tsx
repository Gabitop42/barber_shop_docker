import Plus from "@/assets/icons/plus"
import { formToJSON } from "@/utils/formToJSON"
import { useRef, useState } from "react"

type ServerError = { message?: string }

export const CreateUserModal = () => {
  const [submitting, setSubmitting] = useState(false)
  const [serverError, setServerError] = useState<string | null>(null)
  const dialogRef = useRef<HTMLDialogElement>(null)

  const openModal = () => dialogRef.current?.showModal()
  const closeModal = () => dialogRef.current?.close()

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setServerError(null)

    const form = e.currentTarget
    const data = formToJSON(form)

    if (
      !data.nombre ||
      !data.apellido ||
      !data.email ||
      !data.contraseña ||
      !data.telefono
    ) {
      setServerError("Completa todos los campos obligatorios.")
      return
    }

    console.log(JSON.stringify(data))
    try {
      setSubmitting(true)
      const res = await fetch("http://localhost:9090/api/usuario", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(data),
      })

      if (!res.ok) {
        const err: ServerError = await res
          .json()
          .catch(() => ({ message: `HTTP ${res.status}` }))
        throw new Error(err?.message || "No se pudo crear el usuario")
      }

      form.reset()
      closeModal()
      window.location.reload()
    } catch (err: any) {
      setServerError(err.message || "Error inesperado al crear el usuario")
    } finally {
      setSubmitting(false)
    }
  }

  return (
    <>
      <button
        onClick={openModal}
        className="bg-burdeos hover:bg-burdeos/90 text-marfil flex items-center px-4 py-2 rounded-lg text-sm font-medium transition-colors cursor-pointer"
      >
        <Plus className="h-4 w-4 mr-2" />
        Nuevo Usuario
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

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <label htmlFor="nombre" className="text-marfil block">
                Nombre
              </label>
              <input
                required
                id="nombre"
                name="nombre"
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
                placeholder="Ingresa el nombre"
              />
            </div>

            <div className="space-y-2">
              <label htmlFor="apellido" className="text-marfil block">
                Apellido
              </label>
              <input
                required
                id="apellido"
                name="apellido"
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
                placeholder="Ingresa el apellido"
              />
            </div>
          </div>

          <div className="space-y-2">
            <label htmlFor="email" className="text-marfil block">
              Email
            </label>
            <input
              required
              id="email"
              type="email"
              name="email"
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="ejemplo@barberiabigotes.com"
            />
          </div>

          <div className="space-y-2">
            <label htmlFor="contraseña" className="text-marfil block">
              Contraseña
            </label>
            <input
              required
              id="contraseña"
              type="password"
              name="contraseña"
              autoComplete="new-password"
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="ejemplo@barberiabigotes.com"
            />
          </div>

          <div className="space-y-2">
            <label htmlFor="telefono" className="text-marfil block">
              Teléfono
            </label>
            <input
              required
              id="telefono"
              name="telefono"
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              placeholder="922018160"
            />
          </div>

          {/* <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <label className="text-marfil block">Rol</label>
              <select
                name="rol"
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 rounded-md"
              >
                <option
                  value="BARBERO"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Barbero
                </option>
                <option
                  value="ADMIN"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Administrador
                </option>
              </select>
            </div>

            <div className="space-y-2">
              <label className="text-marfil block">Estado</label>
              <select
                name="estado"
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 rounded-md"
              >
                <option
                  value="ACTIVO"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Activo
                </option>
                <option
                  value="INACTIVO"
                  className="text-marfil hover:bg-marfil/10"
                >
                  Inactivo
                </option>
              </select>
            </div>
          </div> */}

          {serverError && <p className="text-red-400 text-sm">{serverError}</p>}

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
              Crear Usuario
            </button>
          </div>
        </form>
      </dialog>
    </>
  )
}
