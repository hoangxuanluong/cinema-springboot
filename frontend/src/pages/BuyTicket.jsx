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
          <div className='cards'>
            {films.map((film) => (
              <Film key={film.id} item={film} />
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}

export default BuyTicket
