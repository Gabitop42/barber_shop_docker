import type { User } from "@/types/userTypes"
import { useState, useRef, useEffect } from "react"
import { DeleteButton } from "./DeleteButton"

export const UserTable = () => {
  const [users, setUsers] = useState<User[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [openId, setOpenId] = useState<number | null>(null)
  const menuRef = useRef<HTMLDivElement | null>(null)

  useEffect(() => {
    fetchUsers()
  }, [])

  const fetchUsers = async () => {
    try {
      const res = await fetch("http://localhost:9090/api/usuario")
      if (!res.ok) throw new Error("Error al obtener usuarios")
      const data: User[] = await res.json()
      console.log(data)
      setUsers(data)
    } catch (err: any) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const getRoleBadgeColor = (rol: string) => {
    switch (rol) {
      case "BARBERO":
        return "bg-burdeos text-marfil"
      case "ADMIN":
        return "bg-marfil text-carbon"
      default:
        return "bg-gray-600 text-white"
    }
  }

  const getStatusBadgeColor = (estado: boolean) =>
    estado === true ? "bg-green-600 text-white" : "bg-red-600 text-white"

  const onEdit = (u: User) => {
    console.log("Editar:", u)
    setOpenId(null)
  }

  const onDelete = (u: User) => {
    console.log("Eliminar:", u)
    setOpenId(null)
  }

  if (loading) return <p className="text-marfil">Cargando usuarios...</p>
  if (error) return <p className="text-red-400">Error: {error}</p>

  if (users.length === 0) {
    return (
      <div className="text-center py-12">
        <div className="text-marfil/50 text-lg mb-2">
          No se encontraron usuarios
        </div>
        <div className="text-marfil/30 text-sm">
          Intenta ajustar los filtros de búsqueda
        </div>
      </div>
    )
  }

  return (
    <div className="rounded-md border border-marfil/20">
      <div className="w-full overflow-y-visible">
        <table className="w-full border-collapse">
          <thead>
            <tr className="bg-carbon/60">
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Nombre
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Email
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold hidden sm:table-cell">
                Teléfono
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Rol
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Estado
              </th>
              <th className="px-4 py-3 text-right text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Acciones
              </th>
            </tr>
          </thead>

          <tbody className="divide-y divide-marfil/10">
            {users.map((user) => (
              <tr key={user.idUsuario} className="hover:bg-marfil/5">
                <td className="px-4 py-3 text-marfil font-medium whitespace-nowrap">
                  {user.nombre} {user.apellido}
                </td>
                <td className="px-4 py-3 text-marfil/80 whitespace-nowrap">
                  {user.email}
                </td>
                <td className="px-4 py-3 text-marfil/80 whitespace-nowrap hidden sm:table-cell">
                  {user.telefono}
                </td>
                <td className="px-4 py-3">
                  <span
                    className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ${getRoleBadgeColor(
                      user.rol
                    )}`}
                  >
                    {user.rol}
                  </span>
                </td>
                <td className="px-4 py-3">
                  <span
                    className={`inline-flex items-center rounded-full px-2 py-0.5 text-xs font-medium ${getStatusBadgeColor(
                      user.estado
                    )}`}
                  >
                    {user.estado ? "ACTIVO" : "INACTIVO"}
                  </span>
                </td>
                <td className="px-4 py-3 text-right relative">
                  <div className="inline-block text-left" ref={menuRef}>
                    <button
                      type="button"
                      aria-haspopup="menu"
                      aria-expanded={openId === user.idUsuario}
                      onClick={() =>
                        setOpenId(
                          openId === user.idUsuario ? null : user.idUsuario!
                        )
                      }
                      className="inline-flex items-center justify-center rounded-md border border-marfil/20 bg-carbon/40 px-2.5 py-1.5 text-marfil/90 hover:bg-carbon/70 focus:outline-none focus:ring-2 focus:ring-burdeos"
                      title="Acciones"
                    >
                      <svg
                        width="18"
                        height="18"
                        viewBox="0 0 24 24"
                        fill="currentColor"
                        aria-hidden="true"
                      >
                        <circle cx="5" cy="12" r="2" />
                        <circle cx="12" cy="12" r="2" />
                        <circle cx="19" cy="12" r="2" />
                      </svg>
                    </button>

                    {openId === user.idUsuario && (
                      <div
                        role="menu"
                        tabIndex={-1}
                        className="absolute right-0 z-20 mt-2 w-40 origin-top-right rounded-md border border-marfil/20 bg-carbon/95 backdrop-blur-sm shadow-lg focus:outline-none"
                      >
                        <button
                          role="menuitem"
                          onClick={() => onEdit(user)}
                          className="w-full text-left px-3 py-2 text-sm text-marfil hover:bg-marfil/10"
                        >
                          Editar
                        </button>
                        <DeleteButton
                          resource="usuario"
                          id={user.idUsuario!}
                          onDeleted={() => {
                            fetchUsers()
                          }}
                        />
                      </div>
                    )}
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
