import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import Home from './pages/Home'
import Gigs from './pages/Gigs/Gigs'
import TextSummarizer from './pages/TextSummarizer'
import HistoryList from './pages/HistoryList'
import BuyTicket from './pages/BuyTicket'
import SelectSchedule from './pages/selectSchedule/SelectSchedule'
import { AuthContext } from './context/AuthContext'
import React, { useContext } from 'react'
import SelectTicket from './pages/selectTicket/SelectTicket'
import Services from './pages/Services/Services'
import Payment from './pages/payment/Payment'

function App() {
  const { user } = useContext(AuthContext)
  return (
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<Home />} />
        <Route path='/login' element={!user ? <Login /> : <Home />} />
        <Route path='/register' element={!user ? <Register /> : <Home />} />
        <Route path='/change-point' element={user ? <Gigs /> : <Login />} />
        <Route path='/summarize' element={<TextSummarizer />} />
        <Route path='/history' element={<HistoryList />} />
        <Route path='/buy-ticket' element={user ? <BuyTicket /> : <Login />} />
        <Route path='/films/:id/schedules' element={<SelectSchedule />} />
        <Route path='/schedules/:id/tickets' element={<SelectTicket />} />
        <Route path='/services' element={<Services />} />
        <Route path='/payment' element={<Payment />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
