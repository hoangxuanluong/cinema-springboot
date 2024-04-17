import React, { useContext } from 'react'
import './payment.css'
import { useHoaDon } from '../../context/BillContext'
import { AuthContext } from '../../context/AuthContext'
import axios from 'axios'
import moment from 'moment/moment'
import { useNavigate } from 'react-router-dom'
import { Container } from '@mui/material'
import Header from '../../components/Header'
import Footer from '../../components/Footer'
import MainFeaturedPost from '../../components/MainFeaturedPost'

const sections = [
  { title: 'Change Point', url: '/change-point' },
  { title: 'Book Ticket', url: '#' },
]

const mainFeaturedPost = {
  title: 'Forrest Gump',
  description:
    'A man with a low IQ accomplishes great things in his life and witnesses and influences various historical events.',
  image: 'https://source.unsplash.com/random?wallpapers',
  imageText: 'main image description',
  linkText: 'Continue reading…',
}

const Payment = () => {
  const { ve, setVe, chiTietDichVus, setChiTietDichVus } = useHoaDon()
  const { user, dispatch } = useContext(AuthContext)

  const navigate = useNavigate()
  console.log(ve)
  console.log(chiTietDichVus)
  console.log(moment().format('YYYY/MM/DD'))

  const handlePayment = async (e) => {
    e.preventDefault()
    console.log(ve)
    console.log(chiTietDichVus)
    console.log(user)
    const data = {
      thanhVien: {
        email: user.email,
      },
      chiTietDichVus: chiTietDichVus,
      ves: ve,
    }
    console.log(data)
    // console.log(JSON.stringify(data))
    try {
      const res = await axios.post('/api/hoadons', data)
      console.log(data)

      setTimeout(() => navigate('/'), 2000)

      console.log(res.data)
    } catch (error) {
      console.log(error)
    }
  }
  return (
    <>
      <Container maxWidth='lg'>
        <Header title='MMM Cinema' sections={sections} />
        {/* <main>
          <MainFeaturedPost post={mainFeaturedPost} />
        </main> */}
        <div className='payment-container'>
          <div className='wrapper'>
            <div className='member-info' style={{ textTransform: 'uppercase' }}>
              {user.name} - {user.tel}
            </div>
            <div className='movie-info'>
              MOVIE: {ve[0]?.lichChieu?.phim.name}
            </div>
            <div className='movie-info'>DATE: {ve[0]?.lichChieu?.date}</div>
            <div className='movie-info'>
              ROOM: {ve[0]?.lichChieu?.phongChieu.name}
            </div>
            <div className='movie-info'>
              Time: {ve[0]?.lichChieu?.khungGio.timeStart}
            </div>
            <div className='ticket-book'>
              <div className='ticket-title'>Ticket:</div>
              <div className='ticket-details'>
                {ve.map((ele) => (
                  <div key={ve.id} className='ticket-number'>
                    Seat:{ele.ghe.numberChair}
                  </div>
                ))}
              </div>
            </div>
            <div className='service-book'>
              <div className='serviceTitle'>ITEM:</div>
              <div className='serviceInfo'>
                {chiTietDichVus.map((ele, index) => (
                  <div key={index} className='service-details'>
                    {ele.dichVu.name} - {ele.quantity} - {ele.dichVu.price} -{' '}
                    {ele.quantity * ele.dichVu.price}
                  </div>
                ))}
              </div>
            </div>
            <div className='payment-button' onClick={(e) => handlePayment(e)}>
              Payment
            </div>
          </div>
        </div>
      </Container>

      <Footer
        title='Footer'
        description='Something here to give the footer a purpose!'
      />
    </>
  )
}

export default Payment
