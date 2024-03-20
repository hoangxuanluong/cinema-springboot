import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import AddIcon from '@mui/icons-material/Add'
import RemoveIcon from '@mui/icons-material/Remove'

const Service = ({ item, updateCart }) => {
  const [quantity, setQuantity] = useState(0)

  const handleQuantity = (type) => {
    if (type === 'dec') {
      quantity > 1 && setQuantity(quantity - 1)
    } else {
      setQuantity(quantity + 1)
    }
  }

  const handleClick = () => {
    updateCart(item, quantity)
    setQuantity(0)
  }
  return (
    // <Link to='/gig/123' className='link'>
    <div className='gigCard'>
      <img src={item.image} alt='' style={{ objectFit: 'cover' }} />
      <div className='info'>
        <div className='user'>
          {/* <img src={item.pp} alt='' /> */}
          <span>{item.name}</span>
        </div>
        <p>{item.description}</p>
        <div className='star'>
          <img src='./img/star.png' alt='' />
          <span>{item.price}</span>
        </div>
      </div>
      <hr />
      <div className='detail'>
        <div className='amountContainer'>
          <RemoveIcon
            onClick={() => handleQuantity('dec')}
            className='amountIcon'
          />
          <span className='amount'>{quantity}</span>
          <AddIcon
            onClick={() => handleQuantity('inc')}
            className='amountIcon'
          />
        </div>

        {/* <img src='./img/heart.png' alt='' /> */}
        <div className='price'>
          <span onClick={() => handleClick()}>ADD TO CART</span>
          {/* <span>STARTING AT</span>
          <h2>
            $ {item.price}
            <sup>99</sup>
          </h2> */}
        </div>
      </div>
    </div>
    // </Link>
  )
}

export default Service
