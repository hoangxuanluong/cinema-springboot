import React from 'react'

import { Link } from 'react-router-dom'

const Film = ({ item }) => {
  return (
    // <Link to='/gig/123' className='link'>
    <div
      className='gigCard'
      style={{
        height: '420px',
        display: 'flex',
        flexDirection: 'column',
        justifyContent: 'space-between',
        paddingBottom: '10px',
      }}
    >
      <div className='topFilmInfo'>
        <img
          src={
            item.image ||
            'https://www.wowweekend.vn/document_root/upload/articles/image/BrowseContent/StraightNoMixer/202104/n%C4%83m%20centimet%20tr%C3%AAn%20gi%C3%A2y/3.jpg'
          }
          alt=''
          style={{ objectFit: 'cover', height: '200px' }}
        />
        <div className='info'>
          <div className='user'>
            {/* <img src={item.pp} alt='' /> */}
            <span>{item.name}</span>
          </div>
          <p>{item.description}</p>
          <div className='star'>
            <span>{item.productionYear}</span>
          </div>
        </div>
      </div>
      <Link to={`/films/${item.id}/schedules`}>
        <div className='botFilmInfo'>
          <div className='detail'>
            <div className='price'>
              <span style={{ border: '1px solid black', padding: '5px' }}>
                BOOK TICKET
              </span>
            </div>
          </div>
        </div>
      </Link>
    </div>
    // </Link>
  )
}

export default Film
