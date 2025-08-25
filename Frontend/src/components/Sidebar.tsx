import { useState } from "react"

import Dashboard from "@/assets/icons/dashboard"
import Users from "@/assets/icons/users"
import Scissors from "@/assets/icons/scissors"
import ShoppingBag from "@/assets/icons/shopping-bag"
import DollarSign from "@/assets/icons/dollar-sign"
import Calendar from "@/assets/icons/calendar"

const navigation = [
  {
    name: "Dashboard",
    href: "/dashboard",
    icon: Dashboard,
  },
  {
    name: "Usuarios",
    href: "/usuarios",
    icon: Users,
  },
  {
    name: "Servicios",
    href: "/servicios",
    icon: Scissors,
  },
  {
    name: "Ventas",
    href: "/ventas",
    icon: ShoppingBag,
  },
  // {
  //   name: "Comisiones",
  //   href: "/comisiones",
  //   icon: DollarSign,
  // },
  {
    name: "Asistencias",
    href: "/asistencias",
    icon: Calendar,
  },
]

export const Sidebar = () => {
  const [isCollapsed, setIsCollapsed] = useState(false)
  return (
    <div
      className={`flex flex-col h-screen bg-carbon border-r border-burdeos/20 transition-all duration-300 ${
        isCollapsed ? "w-16" : "w-64"
      }`}
    >
      <div className={`flex items-center justify-between p-4 border-b border-burdeos/20 ${isCollapsed ? "justify-center" : ""}`}>
        {!isCollapsed && (
          <div className="flex items-center space-x-3">
            <img
              src="/logo.png"
              alt="Barbería Bigotes"
              width={40}
              height={40}
              className="rounded-lg"
            />
            <div>
              <h2 className="text-marfil font-bold text-lg">Bigotes</h2>
              <p className="text-marfil/60 text-xs">Barbería</p>
            </div>
          </div>
        )}
        <button
          onClick={() => setIsCollapsed(!isCollapsed)}
          className="text-marfil hover:text-burdeos hover:bg-burdeos/10"
        >
          {isCollapsed ? (
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="size-4"
            >
              <path stroke="none" d="M0 0h24v24H0z" fill="none" />
              <path d="M4 6l16 0" />
              <path d="M4 12l16 0" />
              <path d="M4 18l16 0" />
            </svg>
          ) : (
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="24"
              height="24"
              viewBox="0 0 24 24"
              fill="none"
              stroke="currentColor"
              strokeWidth="2"
              strokeLinecap="round"
              strokeLinejoin="round"
              className="size-4"
            >
              <path stroke="none" d="M0 0h24v24H0z" fill="none" />
              <path d="M18 6l-12 12" />
              <path d="M6 6l12 12" />
            </svg>
          )}
        </button>
      </div>

      <nav className="flex-1 p-4 space-y-2">
        {navigation.map((item) => {
          const Icon = item.icon

          return (
            <a
              key={item.name}
              href={item.href}
              className={`flex items-center space-x-3 px-3 py-2 rounded-lg text-sm font-medium transition-colors text-marfil/70 hover:text-marfil hover:bg-burdeos/10 ${isCollapsed && "justify-center"}`}
              // className={cn(
              //   "flex items-center space-x-3 px-3 py-2 rounded-lg text-sm font-medium transition-colors",
              //   isActive
              //     ? "bg-burdeos text-marfil"
              //     : "text-marfil/70 hover:text-marfil hover:bg-burdeos/10",
              //   isCollapsed && "justify-center"
              // )}
            >
              <Icon className="h-5 w-5 flex-shrink-0" />
              {!isCollapsed && <span>{item.name}</span>}
            </a>
          )
        })}
      </nav>

      <div className="p-4 border-t border-burdeos/20 space-y-2">
        {!isCollapsed && (
          <div className="flex items-center space-x-3 px-3 py-2 rounded-lg bg-burdeos/5">
            <div className="w-8 h-8 bg-burdeos rounded-full flex items-center justify-center">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="24"
                height="24"
                viewBox="0 0 24 24"
                fill="none"
                stroke="currentColor"
                strokeWidth="2"
                strokeLinecap="round"
                strokeLinejoin="round"
                className="size-4 text-marfil"
              >
                <path stroke="none" d="M0 0h24v24H0z" fill="none" />
                <path d="M8 7a4 4 0 1 0 8 0a4 4 0 0 0 -8 0" />
                <path d="M6 21v-2a4 4 0 0 1 4 -4h4a4 4 0 0 1 4 4v2" />
              </svg>
            </div>
            <div className="flex-1 min-w-0">
              <p className="text-marfil text-sm font-medium truncate">
                Juan Pérez
              </p>
              <p className="text-marfil/60 text-xs truncate">Barbero</p>
            </div>
          </div>
        )}

        <button
          className={`w-full flex text-marfil/70 rounded-lg items-center justify-center hover:text-marfil hover:bg-burdeos/10 py-2 ${
            isCollapsed && "px-0"
          }`}
          onClick={() => {window.location.href = '/login'}}
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
            className="size-5 block"
          >
            <path stroke="none" d="M0 0h24v24H0z" fill="none" />
            <path d="M14 8v-2a2 2 0 0 0 -2 -2h-7a2 2 0 0 0 -2 2v12a2 2 0 0 0 2 2h7a2 2 0 0 0 2 -2v-2" />
            <path d="M9 12h12l-3 -3" />
            <path d="M18 15l3 -3" />
          </svg>
          {!isCollapsed && <span className="ml-3 block">Cerrar Sesión</span>}
        </button>
      </div>
    </div>
  )
}
