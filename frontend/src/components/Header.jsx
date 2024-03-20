import * as React from 'react'
import PropTypes from 'prop-types'
import Toolbar from '@mui/material/Toolbar'
import Button from '@mui/material/Button'
import IconButton from '@mui/material/IconButton'
import SearchIcon from '@mui/icons-material/Search'
import Typography from '@mui/material/Typography'
import Link from '@mui/material/Link'
import { AuthContext } from '../context/AuthContext'
import { useNavigate } from 'react-router-dom'

function Header(props) {
  const { user, dispatch } = React.useContext(AuthContext)
  const { sections, title } = props
  console.log(user)
  const navigate = useNavigate()

  const handleLogout = async (e) => {
    e.preventDefault()
    dispatch({ type: 'LOGOUT' })
    navigate('/login')
  }

  const navigateHome = () => {
    navigate('/')
  }

  return (
    <React.Fragment>
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
      <Toolbar component='nav' variant='dense' sx={{ overflowX: 'auto' }}>
        {sections.map((section) => (
          <Link
            color='inherit'
            noWrap
            key={section.title}
            variant='body2'
            href={section.url}
            sx={{ p: 1, flexShrink: 0 }}
          >
            {section.title}
          </Link>
        ))}
      </Toolbar>
    </React.Fragment>
  )
}

Header.propTypes = {
  sections: PropTypes.arrayOf(
    PropTypes.shape({
      title: PropTypes.string.isRequired,
      url: PropTypes.string.isRequired,
    })
  ).isRequired,
  title: PropTypes.string.isRequired,
}

export default Header
