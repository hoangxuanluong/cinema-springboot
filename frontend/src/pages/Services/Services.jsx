import React, { useEffect, useRef, useState } from 'react'
import './services.css'
import GigCard from '../../components/gigCard/GigCard'
import { AuthContext } from '../../context/AuthContext'
import axios from 'axios'

import { useNavigate } from 'react-router-dom'
import Toolbar from '@mui/material/Toolbar'
import Button from '@mui/material/Button'
import IconButton from '@mui/material/IconButton'
import SearchIcon from '@mui/icons-material/Search'
import Typography from '@mui/material/Typography'
import Link from '@mui/material/Link'
import Service from '../../components/Service'
import { useHoaDon } from '../../context/BillContext'

const Services = () => {
  const [services, setServices] = useState([])
  const [total, setTotal] = useState(0)
  const [showErrorText, setShowErrorText] = useState(false)
  const [showNoItemText, setShowNoItemText] = useState(false)
  const { ve, setVe, chiTietDichVus, setChiTietDichVus } = useHoaDon()

  console.log(ve)

  const [cart, setCart] = useState([])
  const { user, dispatch } = React.useContext(AuthContext)
  console.log(user)

  const [openFlash, setOpenFlash] = React.useState(false)

  const handleClick = () => {
    setOpenFlash(true)
  }

  const handleClose = (event, reason) => {
    if (reason === 'clickaway') {
      return
    }

    setOpenFlash(false)
  }

  const navigate = useNavigate()

  const handleLogout = async (e) => {
    e.preventDefault()
    dispatch({ type: 'LOGOUT' })
    navigate('/login')
  }

  useEffect(() => {
    const fetchData = async () => {
      let res
      try {
        res = await axios.get('/api/dichvus')
        console.log(res.data)
        setServices(res.data)
      } catch (err) {
        console.log(err)
      }
    }
    fetchData()
  }, [])

  const updateCart = (itemToAdd, quantity) => {
    const itemIndex = cart.findIndex((item) => item.dichVu.id === itemToAdd.id)
    console.log(itemIndex)

    if (itemIndex !== -1) {
      const updatedCart = [...cart]
      updatedCart[itemIndex].quantity += quantity
      setCart(updatedCart)
      setTotal((total) => total + itemToAdd.price * quantity)
    } else {
      setTotal((total) => total + itemToAdd.price * quantity)
      setCart((prevCart) => [
        ...prevCart,
        {
          dichVu: itemToAdd,
          quantity: quantity,
          price: itemToAdd.price,
        },
      ])
    }
  }
  console.log(cart)

  const handleNextButton = () => {
    setChiTietDichVus(cart)
    navigate('/payment')
  }

  const navigateHome = () => {
    navigate('/')
  }

  const handleDelete = (ele) => {
    setCart(cart.filter((item) => item.dichVu.id !== ele.dichVu.id))
    setTotal(total - ele.dichVu.price * ele.quantity)
  }

  return (
    <>
      <Toolbar sx={{ borderBottom: 1, borderColor: 'divider' }}>
        <Button size='small'>Subscribe</Button>
        <Typography
          component='h2'
          variant='h5'
          color='inherit'
          align='center'
          noWrap
          sx={{ flex: 1 }}
          onClick={() => navigateHome()}
          style={{ cursor: 'pointer' }}
        >
          Cinema
        </Typography>

        {!user ? (
          <>
            <IconButton>
              <SearchIcon />
            </IconButton>
            <Link href='/register' sx={{ mr: 2 }}>
              <Button variant='outlined' size='small'>
                Sign up
              </Button>
            </Link>
            <Link href='/login'>
              <Button variant='outlined' size='small'>
                Log in
              </Button>
            </Link>
          </>
        ) : (
          <>
            <span style={{ marginRight: '20px' }}>
              HELLO, {''}
              <span style={{ textTransform: 'uppercase', marginLeft: '5px' }}>
                {user.name}
              </span>
            </span>
            <Button
              variant='outlined'
              size='small'
              onClick={handleLogout}
              sx={{ mr: 1 }}
            >
              Log Out
            </Button>{' '}
            <IconButton>
              <SearchIcon />
            </IconButton>
          </>
        )}
      </Toolbar>
      <div className='gigs'>
        <div className='container'>
          <div className='container-left'>
            <div className='cards'>
              {services.map((service) => (
                <Service
                  key={service.id}
                  item={service}
                  updateCart={updateCart}
                />
              ))}
            </div>
          </div>
          <div className='container-right'>
            <div className='top'>
              <div>
                CUSTOMER:{' '}
                <span style={{ textTransform: 'uppercase' }}>{user.name}</span>
              </div>
              <div>
                TEL:{' '}
                <span style={{ textTransform: 'uppercase' }}>{user.tel}</span>
              </div>
              <div>
                POINT:{' '}
                <span style={{ textTransform: 'uppercase' }}>{user.point}</span>
              </div>
            </div>
            <div className='bottom'>
              <h1 className='summaryTitle'>CART</h1>
              <div className='listItem'>
                {cart.map((item, index) => (
                  <div key={index}>
                    {item.dichVu.name} -- {item.quantity} -- {item.dichVu.price}{' '}
                    -- {item.dichVu.price * item.quantity} --{' '}
                    <span
                      onClick={() => handleDelete(item)}
                      style={{ cursor: 'pointer' }}
                    >
                      delete
                    </span>
                  </div>
                ))}
              </div>
              <div className='summaryItem'>
                <div className='summaryItemText'>Total</div>
                <div className='summaryItemPrice'>{total}</div>
              </div>
              {/* <Link to={user.user ? '/user/payment' : '/login'}> */}
              {/* <button
                className='summaryButton'
                onClick={(e) => handleCheckout(e)}
              >
                CHECKOUT NOW
              </button> */}
              <button
                className='summaryButton'
                onClick={() => handleNextButton()}
              >
                NEXT
              </button>
              {showErrorText && (
                <div className='div' style={{ color: '#eb4034' }}>
                  Point exceed !!!!!!
                </div>
              )}
              {showNoItemText && (
                <div className='div' style={{ color: '#eb4034' }}>
                  No Item !!!!!!
                </div>
              )}
            </div>
            {/* </Link> */}
          </div>
        </div>
      </div>
    </>
  )
}

export default Services
