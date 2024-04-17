import React from 'react'

import { Link } from 'react-router-dom'

const Film = ({ item }) => {
  return (
    // <Link to='/gig/123' className='link'>
    <div
      className='gigCard'
      style={{
        height: '470px',
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
      <div className='botFilmInfo'>
        <div className='detail'>
          <div className='price' style={{ gap: '20px', display: 'flex' }}>
            <Link to={`/films/${item.id}`}>
              <span style={{ border: '1px solid black', padding: '5px' }}>
                VIEW DETAILS
              </span>
            </Link>
            <Link to={`/films/${item.id}/schedules`}>
              <span style={{ border: '1px solid black', padding: '5px' }}>
                BOOK TICKET
              </span>
            </Link>
          </div>
        </div>
      </div>
    </div>
    // </Link>
  )
}

export default Film
