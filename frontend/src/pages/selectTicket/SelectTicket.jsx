import axios from 'axios'
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'
import './selectTicket.css'
import { useHoaDon } from '../../context/BillContext'

const SelectTicket = () => {
  const [tickets, setTickets] = useState([])
  const [bookedTickets, setBookedTickets] = useState([])
  const idLichChieu = useLocation().pathname.split('/')[2]
  const { ve, setVe } = useHoaDon()
  const navigate = useNavigate()

  useEffect(() => {
    const fetchData = async () => {
      try {
        const res = await axios.get(`/api/ves/lichchieus/${idLichChieu}`)
        setTickets(res.data)
        console.log(res)
      } catch (err) {
        console.log(err)
      }
    }
    fetchData()
  }, [idLichChieu])

  const handleTicketClick = (ticket) => {
    // Check if the ticket is already booked
    const isBooked = bookedTickets.includes(ticket)

    if (isBooked) {
      // If already booked, remove it from the bookedTickets list
      const updatedBookedTickets = bookedTickets.filter((id) => id !== ticket)
      setBookedTickets(updatedBookedTickets)
      setVe(updatedBookedTickets)
    } else {
      // If not booked, add it to the bookedTickets list
      setBookedTickets([...bookedTickets, ticket])
      setVe([...bookedTickets, ticket])
    }
  }
  // console.log(ve)
  // console.log(tickets)
  // console.log(bookedTickets)

  const handleNextButton = () => {
    navigate('/services')
  }

  return (
    <>
      <div className='container'>
        <div className='wrapper'>
          <div className='tickets'>
            {tickets?.map((ticket, index) => (
              <div key={index}>
                {ticket?.hoaDon ? (
                  <div className='ticket selected'></div>
                ) : (
                  <div
                    onClick={() => handleTicketClick(ticket)}
                    className={ve.includes(ticket) ? 'ticket booked' : 'ticket'}
                  ></div>
                )}
              </div>
            ))}
          </div>
          <div className='button-next' onClick={() => handleNextButton()}>
            Next
          </div>
        </div>
      </div>
    </>
  )
}
export default SelectTicket
