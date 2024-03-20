import React, { createContext, useState, useContext } from 'react'

// Tạo context
const HoaDonContext = createContext()

// Tạo Provider
const HoaDonProvider = ({ children }) => {
  const [thanhVien, setThanhVien] = useState({})
  const [ve, setVe] = useState([])
  const [chiTietDichVus, setChiTietDichVus] = useState([])

  return (
    <HoaDonContext.Provider
      value={{
        thanhVien,
        setThanhVien,
        ve,
        setVe,
        chiTietDichVus,
        setChiTietDichVus,
      }}
    >
      {children}
    </HoaDonContext.Provider>
  )
}

const useHoaDon = () => {
  const context = useContext(HoaDonContext)
  if (!context) {
    throw new Error('useHoaDon must be used within a HoaDonProvider')
  }
  return context
}

export { HoaDonProvider, useHoaDon }
export default HoaDonContext
