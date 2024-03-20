import React, { useEffect, useRef, useState } from 'react'
import './Gigs.scss'
import { gigs } from '../../data'
import GigCard from '../../components/gigCard/GigCard'
import { AuthContext } from '../../context/AuthContext'
import axios from 'axios'

import Stack from '@mui/material/Stack'
import Snackbar from '@mui/material/Snackbar'
import MuiAlert from '@mui/material/Alert'
import { useNavigate } from 'react-router-dom'
import Header from '../../components/Header'
import Toolbar from '@mui/material/Toolbar'
import Button from '@mui/material/Button'
import IconButton from '@mui/material/IconButton'
import SearchIcon from '@mui/icons-material/Search'
import Typography from '@mui/material/Typography'
import Link from '@mui/material/Link'

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant='filled' {...props} />
})

function Gigs() {
  const [sort, setSort] = useState('sales')
  const [open, setOpen] = useState(false)
  const [services, setServices] = useState([])
  const [total, setTotal] = useState(0)
  const [showErrorText, setShowErrorText] = useState(false)
  const [showNoItemText, setShowNoItemText] = useState(false)

  const minRef = useRef()
  const maxRef = useRef()

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
    const itemIndex = cart.findIndex(
      (item) => item?.dichVu?.id === itemToAdd?.id
    )
    console.log(itemIndex)

    if (itemIndex !== -1) {
      // Nếu mặt hàng đã có trong giỏ hàng, cập nhật số lượng
      const updatedCart = [...cart]
      updatedCart[itemIndex].quantity += quantity
      setCart(updatedCart)
      setTotal((total) => total + itemToAdd.point * quantity)
    } else {
      setTotal((total) => total + itemToAdd.point * quantity)
      setCart((prevCart) => [
        ...prevCart,
        {
          dichVu: itemToAdd,
          quantity: quantity,
          point: itemToAdd.point,
        },
      ])
    }
  }
  console.log(cart)

  const handleCheckout = async (event) => {
    event.preventDefault()
    if (total == 0) {
      setShowNoItemText(true)
    } else if (total > user.point) {
      setShowErrorText(true)
      setTimeout(() => setShowErrorText(false), 3000)
    } else {
      const data = { thanhVien: user, chiTietDois: cart }
      console.log(data)
      // user.point -= total
      try {
        const res = await axios.post('/api/hoadondois', data)
        dispatch({ type: 'UPDATE_POINT', payload: user.point - total })
        setCart([])
        setTotal(0)
        setOpenFlash(true)
        setTimeout(() => navigate('/'), 2000)

        // user.point -= total
        console.log(res.data)
      } catch (error) {
        console.log(error)
      }
    }
  }

  const reSort = (type) => {
    setSort(type)
    setOpen(false)
  }

  const apply = () => {
    console.log(minRef.current.value)
    console.log(maxRef.current.value)
  }

  const navigateHome = () => {
    navigate('/')
  }

  const handleDelete = (ele) => {
    setCart(cart.filter((item) => item.dichVu.id !== ele.dichVu.id))
    setTotal(total - ele.dichVu.point * ele.quantity)
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
            {/* <span className='breadcrumbs'>Liverr - Graphics & Design </span>
            <h1>AI Artists</h1>
            <p>
              Explore the boundaries of art and technology with Liverr's AI
              artists
            </p> */}
            {/* <div className='menu'>
              <div className='left'>
                <span>Budget</span>
                <input ref={minRef} type='number' placeholder='min' />
                <input ref={maxRef} type='number' placeholder='max' />
                <button onClick={apply}>Apply</button>
              </div>
              <div className='right'>
                <span className='sortBy'>Sort by</span>
                <span className='sortType'>
                  {sort === 'sales' ? 'Best Selling' : 'Newest'}
                </span>
                <img
                  src='./img/down.png'
                  alt=''
                  onClick={() => setOpen(!open)}
                />
                {open && (
                  <div className='rightMenu'>
                    {sort === 'sales' ? (
                      <span onClick={() => reSort('createdAt')}>Newest</span>
                    ) : (
                      <span onClick={() => reSort('sales')}>Best Selling</span>
                    )}
                    <span onClick={() => reSort('sales')}>Popular</span>
                  </div>
                )}
              </div>
            </div> */}
            <div className='cards'>
              {services.map((service) => (
                <GigCard
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
                    {item.dichVu.name} -- {item.quantity} -- {item.dichVu.point}{' '}
                    -- {item.dichVu.point * item.quantity} --{' '}
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
                <div className='summaryItemPrice'>{total} POINT</div>
              </div>
              {/* <Link to={user.user ? '/user/payment' : '/login'}> */}
              <button
                className='summaryButton'
                onClick={(e) => handleCheckout(e)}
              >
                CHECKOUT NOW
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
      <Stack spacing={2} sx={{ width: '100%' }}>
        <Snackbar
          open={openFlash}
          autoHideDuration={2000}
          onClose={handleClose}
        >
          <Alert
            onClose={handleClose}
            severity='success'
            sx={{ width: '100%' }}
            anchororigin={{ vertical: 'top', horizontal: 'right' }}
          >
            Change point successfully!!!
          </Alert>
        </Snackbar>
        {/* <Alert severity='success'>This is a success message!</Alert> */}
      </Stack>
    </>
  )
}

export default Gigs
