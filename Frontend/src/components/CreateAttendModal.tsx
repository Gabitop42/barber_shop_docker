import Clock from "@/assets/icons/clock"
import { useRef, useState } from "react"

export const CreateAttendModal = () => {
  const [errors, setErrors] = useState<Record<string, string>>({})
  const dialogRef = useRef<HTMLDialogElement>(null)

  const openModal = () => {
    dialogRef.current?.showModal()
  }

  const closeModal = () => {
    dialogRef.current?.close()
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
        <Clock className="h-4 w-4 mr-2" />
        Registrar asistencia
      </button>

      <dialog
        ref={dialogRef}
        className="bg-carbon m-auto backdrop:bg-black/40 border-marfil/20 max-w-lg p-6 rounded-lg w-full border"
      >
        <h3 className="text-marfil flex items-center justify-between text-lg font-medium mb-4">
          Registrar asistencia
        </h3>

        <form onSubmit={handleSubmit} className="space-y-4">
          <div className="space-y-2">
            <label htmlFor="fecha" className="text-marfil block">
              Fecha
            </label>
            <input
              id="fecha"
              type="date"
              // value={formData.fecha}
              // onChange={(e) => handleChange("fecha", e.target.value)}
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              required
            />
          </div>

          <div className="space-y-2">
            <label htmlFor="barbero" className="text-marfil block">
              Barbero
            </label>
            <select
            className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
            // value={formData.idUsuario}
            // onValueChange={(value) => handleChange("idUsuario", value)}
            >
              <option value="1">Carlos Mendoza</option>
              <option value="2">Miguel Torres</option>
              <option value="3">Roberto Silva</option>
              <option value="4">Ana García</option>
            </select>
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <label htmlFor="horaIngreso" className="text-marfil block">
                Hora de Entrada
              </label>
              <input
                id="horaIngreso"
                type="time"
                // value={formData.horaIngreso}
                // onChange={(e) => handleChange("horaIngreso", e.target.value)}
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
                required
              />
            </div>

            <div className="space-y-2">
              <label htmlFor="horaSalida" className="text-marfil block">
                Hora de Salida
              </label>
              <input
                id="horaSalida"
                type="time"
                // value={formData.horaSalida}
                // onChange={(e) => handleChange("horaSalida", e.target.value)}
                className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
              />
            </div>
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
              Registrar
            </button>
          </div>
        </form>
      </dialog>
    </>
  )
}
