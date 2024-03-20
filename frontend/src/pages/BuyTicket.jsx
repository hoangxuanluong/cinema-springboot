import React, { useEffect, useRef, useState } from 'react'
// import './Gigs.scss'
// import { gigs } from '../../data'
import GigCard from '../components/gigCard/GigCard'
import { AuthContext } from '../context/AuthContext'
import axios from 'axios'
import Film from '../components/Film'
const BuyTicket = () => {
  const [sort, setSort] = useState('sales')
  const [open, setOpen] = useState(false)
  const [films, setFilms] = useState([])

  const minRef = useRef()
  const maxRef = useRef()

  const { user, dispatch } = React.useContext(AuthContext)
  console.log(user)

  useEffect(() => {
    const fetchData = async () => {
      let res
      try {
        res = await axios.get('/api/phims')
        console.log(res.data)
        setFilms(res.data)
      } catch (err) {
        console.log(err)
      }
    }
    fetchData()
  }, [])

  const reSort = (type) => {
    setSort(type)
    setOpen(false)
  }

  const apply = () => {
    console.log(minRef.current.value)
    console.log(maxRef.current.value)
  }
  return (
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
              <img src='./img/down.png' alt='' onClick={() => setOpen(!open)} />
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
            {films.map((film) => (
              <Film key={film.id} item={film} />
            ))}
          </div>
        </div>

        {/* </Link> */}
      </div>
    </div>
  )
}

export default BuyTicket
