import type { Sale } from "@/types/saleTypes"
import { useState, useRef, useEffect } from "react"
import { DeleteButton } from "./DeleteButton"

export const SaleTable = () => {
  const [sales, setSales] = useState<Sale[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)
  const [openId, setOpenId] = useState<number | null>(null)
  const menuRef = useRef<HTMLDivElement | null>(null)

  useEffect(() => {
    fetchSales()
  }, [])

  const fetchSales = async () => {
    try {
      const res = await fetch("http://localhost:8080/api/venta")
      if (!res.ok) throw new Error("Error al obtener ventas")
      const data: Sale[] = await res.json()
      console.log(data)
      setSales(data)
    } catch (err: any) {
      setError(err.message)
    } finally {
      setLoading(false)
    }
  }

  const onEdit = (u: Sale) => {
    console.log("Editar:", u)
    setOpenId(null)
  }

  const onDelete = (u: Sale) => {
    console.log("Eliminar:", u)
    setOpenId(null)
  }

  if (loading) return <p className="text-marfil">Cargando ventas...</p>
  if (error) return <p className="text-red-400">Error: {error}</p>

  if (sales.length === 0) {
    return (
      <div className="text-center py-12">
        <div className="text-marfil/50 text-lg mb-2">
          No se encontraron ventas
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
                Fecha
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold hidden sm:table-cell">
                Ingreso
              </th>
              <th className="px-4 py-3 text-left text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Salida
              </th>
              <th className="px-4 py-3 text-right text-[11px] md:text-xs uppercase tracking-wide text-marfil/70 font-semibold">
                Acciones
              </th>
            </tr>
          </thead>

          <tbody className="divide-y divide-marfil/10">
            {sales.map((sale) => (
              <tr key={sale.id} className="hover:bg-marfil/5">
                <td className="px-4 py-3 text-marfil font-medium whitespace-nowrap">
                  {sale.id}
                </td>
                <td className="px-4 py-3 text-marfil/80 whitespace-nowrap">
                  {sale.fecha}
                </td>
                <td className="px-4 py-3 text-marfil/80 whitespace-nowrap hidden sm:table-cell">
                  {sale.idUsuario}
                </td>
                <td className="px-4 py-3 text-marfil/80 whitespace-nowrap hidden sm:table-cell">
                  {sale.total}
                </td>
                <td className="px-4 py-3 text-right relative">
                  <div className="inline-block text-left" ref={menuRef}>
                    <button
                      type="button"
                      aria-haspopup="menu"
                      aria-expanded={openId === sale.idUsuario}
                      onClick={() =>
                        setOpenId(
                          openId === sale.idUsuario ? null : sale.idUsuario!
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

                    {openId === sale.idUsuario && (
                      <div
                        role="menu"
                        tabIndex={-1}
                        className="absolute right-0 z-20 mt-2 w-40 origin-top-right rounded-md border border-marfil/20 bg-carbon/95 backdrop-blur-sm shadow-lg focus:outline-none"
                      >
                        <button
                          role="menuitem"
                          onClick={() => onEdit(sale)}
                          className="w-full text-left px-3 py-2 text-sm text-marfil hover:bg-marfil/10"
                        >
                          Editar
                        </button>
                        <DeleteButton
                          resource="venta"
                          id={sale.id}
                          onDeleted={() => {
                            fetchSales()
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