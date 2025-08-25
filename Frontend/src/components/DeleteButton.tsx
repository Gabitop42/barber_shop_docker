import { useState } from "react"

type Props = {
  resource: "usuario" | "servicio-producto" | "venta" | "asistencia"
  id: number | string
  onDeleted?: () => void
}

export const DeleteButton = ({ resource, id, onDeleted }: Props) => {
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState<string | null>(null)

  const handleDelete = async () => {
    const confirm = window.confirm(`¿Seguro que deseas eliminar este ${resource}?`)
    if (!confirm) return

    setLoading(true)
    setError(null)

    try {
      const res = await fetch(`http://localhost:8080/api/${resource}/${id}`, {
        method: "DELETE",
      })

      if (!res.ok) {
        throw new Error(`Error al eliminar ${resource}`)
      }

      if (onDeleted) onDeleted()
    } catch (err: any) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  return (
    <div>
      <button
        onClick={handleDelete}
        disabled={loading}
        className="w-full text-left px-3 py-2 text-sm text-red-300 hover:bg-red-400/10 disabled:opacity-50"
      >
        {loading ? "Eliminando..." : "Eliminar"}
      </button>
      {error && <p className="text-xs text-red-400 mt-1">{error}</p>}
    </div>
  )
}
