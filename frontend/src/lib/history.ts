// import { baseUrl } from './api'

export async function starPress(iata: string) {
  const history = await getSearchHistory()
  if (!history.includes(iata)) {
    history.push(iata)
  } else {
    history.splice(history.indexOf(iata), 1)
  }

  // return fetch(`${baseUrl}/history`, {
  //     method: 'POST',
  //     headers: {
  //         'Content-Type': 'application/json',
  //     },
  //     body: JSON.stringify(history),
  // })
  localStorage.setItem('searchHistory', JSON.stringify(history))
}

export async function getSearchHistory() {
  // return fetch(`${baseUrl}/history`)
  return JSON.parse(localStorage.getItem('searchHistory') || '[]')
}
