import * as React from "react"

const sizeClasses = {
  sm: {
    wrapper: "w-7.5 h-4",
    circle: "w-3.5 h-3.5 top-0.25 left-0.25 peer-checked:translate-x-3.5",
  },
  md: {
    wrapper: "w-10 h-6",
    circle: "w-5 h-5 top-0.5 left-0.5 peer-checked:translate-x-4",
  },
  lg: {
    wrapper: "w-12 h-7",
    circle: "w-6 h-6 top-0.5 left-0.5 peer-checked:translate-x-5",
  },
} as const

const themesClasses = {
  carbon:
    "peer-checked:bg-carbon peer-checked:border peer-checked:border-white/20",
  burdeos:
    "peer-checked:bg-burdeos peer-checked:border peer-checked:border-white/20",
  marfil:
    "peer-checked:bg-marfil peer-checked:border peer-checked:border-white/20",
} as const

type BaseProps = Omit<
  React.InputHTMLAttributes<HTMLInputElement>,
  "size" | "checked" | "defaultChecked" | "onChange"
> & {
  id?: string
  name?: string
  disabled?: boolean
  switchSize?: keyof typeof sizeClasses
  theme?: keyof typeof themesClasses
  className?: string
  /** callback amigable: te paso el boolean ya resuelto */
  onCheckedChange?: (
    checked: boolean,
    e: React.ChangeEvent<HTMLInputElement>
  ) => void
}

/** Variante CONTROLADA */
type Controlled = {
  checked: boolean
  defaultChecked?: never
}

/** Variante NO CONTROLADA */
type Uncontrolled = {
  defaultChecked?: boolean
  checked?: never
}

type SwitchProps = BaseProps & (Controlled | Uncontrolled)

export const Switch = React.forwardRef<HTMLInputElement, SwitchProps>(
  (
    {
      id,
      name,
      disabled = false,
      switchSize = "md",
      theme = "carbon",
      className = "",
      onCheckedChange,
      // controlled/uncontrolled props mezcladas en el resto
      ...rest
    },
    ref
  ) => {
    const isControlled = "checked" in rest
    const sizeClass = sizeClasses[switchSize] ?? sizeClasses.md
    const themeClass = themesClasses[theme] ?? themesClasses.carbon

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
      onCheckedChange?.(e.target.checked, e)
      // Si además quieres propagar el onChange nativo si lo envían:
      ;(rest as any).onChange?.(e)
    }

    return (
      <label
        htmlFor={id}
        className={`relative inline-block ${sizeClass.wrapper} ${
          disabled ? "opacity-50 cursor-not-allowed" : "cursor-pointer"
        } ${className}`}
      >
        {isControlled ? (
          <input
            ref={ref}
            id={id}
            name={name}
            type="checkbox"
            disabled={disabled}
            className="sr-only peer"
            onChange={handleChange}
            checked={rest.checked}
            {...(rest as Omit<typeof rest, "checked">)}
          />
        ) : (
          <input
            ref={ref}
            id={id}
            name={name}
            type="checkbox"
            disabled={disabled}
            className="sr-only peer"
            onChange={handleChange}
            defaultChecked={rest.defaultChecked}
            {...(rest as Omit<typeof rest, "defaultChecked">)}
          />
        )}

        {/* Track */}
        <div
          aria-hidden="true"
          className={`${sizeClass.wrapper} bg-gray-400 ${themeClass} rounded-full transition-colors duration-300 focus-within:ring-2 focus-within:ring-white/20`}
        />

        {/* Thumb */}
        <div
          aria-hidden="true"
          className={`absolute bg-white rounded-full transition-transform duration-300 ${sizeClass.circle} shadow`}
        />
      </label>
    )
  }
)
Switch.displayName = "Switch"
