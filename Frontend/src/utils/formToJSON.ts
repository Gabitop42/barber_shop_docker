export function formToJSON(form: HTMLFormElement) {
  const fd = new FormData(form)
  const data: Record<string, any> = {}

  for (const [key, value] of fd.entries()) {
    if (data[key]) {
      if (Array.isArray(data[key])) data[key].push(value)
      else data[key] = [data[key], value]
    } else {
      data[key] = value
    }
  }

  form.querySelectorAll("input[type=checkbox]").forEach((cb) => {
    const checkbox = cb as HTMLInputElement
    if (!data[checkbox.name]) {
      data[checkbox.name] = false
    } else if (data[checkbox.name] === "on") {
      data[checkbox.name] = true
    }
  })

  return data
}
