import React, { useEffect } from 'react'
import { useState } from 'react'
import './FilmDetail.scss'
import AddShoppingCartIcon from '@mui/icons-material/AddShoppingCart'
import FavoriteBorderIcon from '@mui/icons-material/FavoriteBorder'
import BalanceIcon from '@mui/icons-material/Balance'
// import useFetch from '../../hooks/useFetch'
import { Link, useParams } from 'react-router-dom'
// import { useDispatch } from 'react-redux'
import axios from 'axios'
import Footer from '../../components/Footer'
import Header from '../../components/Header'
import { Container } from '@mui/material'
import MainFeaturedPost from '../../components/MainFeaturedPost'
import BuyTicket from '../BuyTicket'

const sections = [
  { title: 'Change Point', url: '/change-point' },
  { title: 'Book Ticket', url: '#' },
  // { title: 'Culture', url: '#' },
  // { title: 'Business', url: '#' },
  // { title: 'Politics', url: '#' },
  // { title: 'Opinion', url: '#' },
  // { title: 'Science', url: '#' },
  // { title: 'Health', url: '#' },
  // { title: 'Style', url: '#' },
  // { title: 'Travel', url: '#' },
]

const featuredPosts = [
  {
    title: 'Featured post',
    date: 'Nov 12',
    description:
      'This is a wider card with supporting text below as a natural lead-in to additional content.',
    image: 'https://source.unsplash.com/random?wallpapers',
    imageLabel: 'Image Text',
  },
  {
    title: 'Post title',
    date: 'Nov 11',
    description:
      'This is a wider card with supporting text below as a natural lead-in to additional content.',
    image: 'https://source.unsplash.com/random?wallpapers',
    imageLabel: 'Image Text',
  },
]

const mainFeaturedPost = {
  title: 'Forrest Gump',
  description:
    'A man with a low IQ accomplishes great things in his life and witnesses and influences various historical events.',
  image: 'https://source.unsplash.com/random?wallpapers',
  imageText: 'main image description',
  linkText: 'Continue reading…',
}

const FilmDetail = () => {
  const id = useParams().id
  const [selectedImg, setSelectedImg] = useState('img')
  const [quantity, setQuantity] = useState(1)
  const [film, setFilm] = useState({})
  const [data, setData] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState(false)

  // const dispatch = useDispatch()
  // const { data, loading, error } = useFetch(`/products/${id}?populate=*`)

  useEffect(() => {
    const fetchData = async () => {
      let res
      try {
        setLoading(true)
        res = await axios.get(`/api/phims/${id}`)
        console.log(res.data)
        setData(res.data)
      } catch (err) {
        setError(true)
        console.log(err)
      }
      setLoading(false)
    }
    fetchData()
  }, [id])

  return (
    <>
      <Container maxWidth='lg'>
        <Header title='MMM Cinema' sections={sections} />
        <main>
          <MainFeaturedPost post={mainFeaturedPost} />
        </main>
        <div className='product'>
          {loading ? (
            'loading'
          ) : (
            <>
              <div className='left'>
                {/* <div className='images'>
              <img
                src={data?.image}
                alt=''
                onClick={(e) => setSelectedImg('img')}
              />
              <img
                src={data?.image}
                alt=''
                onClick={(e) => setSelectedImg('img2')}
              />
            </div> */}
                <div className='mainImg'>
                  <img
                    src={
                      data?.image ||
                      'https://www.wowweekend.vn/document_root/upload/articles/image/BrowseContent/StraightNoMixer/202104/n%C4%83m%20centimet%20tr%C3%AAn%20gi%C3%A2y/3.jpg'
                    }
                    alt=''
                  />
                </div>
              </div>
              <div className='right'>
                <h1>{data?.name}</h1>
                {/* <span className='price'>${data?.attributes?.price}</span> */}
                <p>{data?.description}</p>
                <p>{data?.type}</p>
                <p>{data?.productionYear}</p>
                {/* <div className='quantity'>
              <button
                onClick={() =>
                  setQuantity((prev) => (prev === 1 ? 1 : prev - 1))
                }
              >
                -
              </button>
              {quantity}
              <button onClick={() => setQuantity((prev) => prev + 1)}>+</button>
            </div> */}
                <Link to={`/films/${id}/schedules`}>
                  <button className='add' onClick={() => {}}>
                    <div> BUY TICKET</div>
                  </button>
                </Link>
                {/* <div className='links'>
              <div className='item'>
                <FavoriteBorderIcon /> ADD TO WISH LIST
              </div>
              <div className='item'>
                <BalanceIcon /> ADD TO COMPARE
              </div>
            </div> */}
                {/* <div className='info'>
              <span>Vendor: Polo</span>
              <span>Product Type: T-Shirt</span>
              <span>Tag: T-Shirt, Women, Top</span>
            </div> */}
                {/* <hr />
            <div className='info'>
              <span>DESCRIPTION</span>
              <hr />
              <span>ADDITIONAL INFORMATION</span>
              <hr />
              <span>FAQ</span>
            </div> */}
              </div>
            </>
          )}
        </div>
      </Container>

      <Footer
        title='Footer'
        description='Something here to give the footer a purpose!'
      />
    </>
  )
}

export default FilmDetail
