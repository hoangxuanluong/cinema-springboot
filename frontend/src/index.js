import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App'
import './index.css'
import { AuthContextProvider } from './context/AuthContext'
import { HoaDonProvider } from './context/BillContext'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <AuthContextProvider>
    <HoaDonProvider>
      <App />
    </HoaDonProvider>
  </AuthContextProvider>
)
