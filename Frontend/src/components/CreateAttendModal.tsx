import Clock from "@/assets/icons/clock"
import type { UserAttend } from "@/types/userTypes"
import { formToJSON } from "@/utils/formToJSON"
import { useEffect, useRef, useState } from "react"

type ServerError = { message?: string }

export const CreateAttendModal = () => {
  const [serverError, setServerError] = useState<string | null>(null)
  const [submitting, setSubmitting] = useState(false)
  const [users, setUsers] = useState<UserAttend[]>([])
  const [loadingUsers, setLoadingUsers] = useState(true)
  const [userErr, setUserErr] = useState<string | null>(null)
  const dialogRef = useRef<HTMLDialogElement>(null)

  const openModal = () => dialogRef.current?.showModal()
  const closeModal = () => dialogRef.current?.close()

  useEffect(() => {
    const loadUsers = async () => {
      try {
        const res = await fetch("http://localhost:9090/api/usuario")
        if (!res.ok) throw new Error("No se pudo cargar usuarios")
        const data = await res.json()
        setUsers(data)
      } catch (e: any) {
        setUserErr(e.message ?? "Error al cargar usuarios")
      } finally {
        setLoadingUsers(false)
      }
    }
    loadUsers()
  }, [])

  const toLocalDateTime = (dateStr?: string, timeStr?: string) => {
    if (!dateStr || !timeStr) return undefined
    const hhmm = timeStr.length === 5 ? `${timeStr}:00` : timeStr
    return `${dateStr}T${hhmm}`
  }

  const handleSubmit = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault()
    setServerError(null)

    const form = e.currentTarget
    const data = formToJSON(form)

    if (!data.fecha || !data.idUsuario) {
      setServerError("Completa los campos obligatorios (fecha y barbero).")
      return
    }

    const horaIngreso = toLocalDateTime(data.fecha, data.horaIngreso)
    const horaSalida = toLocalDateTime(data.fecha, data.horaSalida)

    if (horaIngreso) {
      const now = new Date()
      const hi = new Date(horaIngreso)
      if (hi.getTime() < now.getTime()) {
        setServerError("La hora de ingreso debe ser presente o futura.")
        return
      }
    }
    if (horaIngreso && horaSalida) {
      const hi = new Date(horaIngreso)
      const hs = new Date(horaSalida)
      if (hs <= hi) {
        setServerError(
          "La hora de salida debe ser posterior a la hora de ingreso."
        )
        return
      }
      const now = new Date()
      if (hs.getTime() > now.getTime()) {
        setServerError("La hora de salida no puede ser futura.")
        return
      }
    }

    const payload: any = {
      fecha: data.fecha,
      idUsuario: Number(data.idUsuario),
    }
    if (horaIngreso) payload.horaIngreso = horaIngreso
    if (horaSalida) payload.horaSalida = horaSalida

    try {
      setSubmitting(true)
      const res = await fetch("http://localhost:8080/api/asistencia", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      })

      if (!res.ok) {
        const err: ServerError = await res
          .json()
          .catch(() => ({ message: `HTTP ${res.status}` }))
        throw new Error(err?.message || "No se pudo registrar la asistencia")
      }

      form.reset()
      closeModal()
      window.location.reload()
    } catch (err: any) {
      setServerError(err.message || "Error inesperado al crear la asistencia")
    } finally {
      setSubmitting(false)
    }
  }

  const pad = (n: number) => String(n).padStart(2, "0")
  const today = new Date()
  const defaultDate = today.toISOString().slice(0, 10)
  const plusOneMinute = new Date(today.getTime() + 60 * 1000)
  const defaultTime = `${pad(plusOneMinute.getHours())}:${pad(
    plusOneMinute.getMinutes()
  )}`

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
              name="fecha"
              type="date"
              required
              defaultValue={defaultDate}
              className="bg-carbon/50 border-marfil/30 text-marfil placeholder:text-marfil/50 border pl-2 p-1 w-full rounded-md"
            />
          </div>

          <div className="space-y-2">
            <label htmlFor="idUsuario" className="text-marfil block">
              Barbero
            </label>
            <select
              id="idUsuario"
              name="idUsuario"
              required
              className="bg-carbon/50 border-marfil/30 text-marfil border pl-2 p-1 w-full rounded-md"
              disabled={loadingUsers}
            >
              {loadingUsers && <option>Cargando...</option>}
              {!loadingUsers && users.length === 0 && (
                <option value="">Sin usuarios</option>
              )}
              {!loadingUsers &&
                users.map((u) => (
                  <option key={u.idUsuario} value={u.idUsuario}>
                    {u.nombre} {u.apellido}
                  </option>
                ))}
            </select>
            {userErr && <p className="text-red-400 text-sm mt-1">{userErr}</p>}
          </div>

          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2">
              <label htmlFor="horaIngreso" className="text-marfil block">
                Hora de Entrada
              </label>
              <input
                id="horaIngreso"
                name="horaIngreso"
                type="time"
                defaultValue={defaultTime}
                className="bg-carbon/50 border-marfil/30 text-marfil border pl-2 p-1 w-full rounded-md"
              />
            </div>

            <div className="space-y-2">
              <label htmlFor="horaSalida" className="text-marfil block">
                Hora de Salida
              </label>
              <input
                id="horaSalida"
                name="horaSalida"
                type="time"
                className="bg-carbon/50 border-marfil/30 text-marfil border pl-2 p-1 w-full rounded-md"
              />
              <p className="text-xs text-marfil/60">
                Opcional. Debe ser posterior a la entrada y no futura.
              </p>
            </div>
          </div>

          {serverError && <p className="text-red-400 text-sm">{serverError}</p>}

          <div className="flex justify-end gap-3 pt-4">
            <button
              type="button"
              onClick={closeModal}
              className="bg-marfil/10 text-marfil border-marfil/20 hover:bg-marfil/20 py-1 px-2 rounded-md"
              disabled={submitting}
            >
              Cancelar
            </button>
            <button
              type="submit"
              className="bg-burdeos hover:bg-burdeos/90 text-marfil py-1 px-2 rounded-md disabled:opacity-60"
              disabled={submitting || loadingUsers || !!userErr}
            >
              {submitting ? "Registrando..." : "Registrar"}
            </button>
          </div>
        </form>
      </dialog>
    </>
  )
}
