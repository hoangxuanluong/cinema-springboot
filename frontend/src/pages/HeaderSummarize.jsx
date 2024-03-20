import React from 'react'
import { AppBar, Toolbar, Typography, Button, Link } from '@mui/material'
import { Link as RouterLink } from 'react-router-dom'

const HeaderSummarize = () => {
  return (
    <AppBar position='static'>
      <Toolbar>
        <Typography variant='h6' component='div' sx={{ flexGrow: 1 }}>
          Ứng dụng Tóm tắt Văn bản
        </Typography>
        <Button color='inherit' component={RouterLink} to='/history'>
          <Link underline='none' color='inherit'>
            Xem Lịch Sử
          </Link>
        </Button>
        <Button color='inherit' component={RouterLink} to='/summarize'>
          <Link underline='none' color='inherit'>
            Tóm Tắt
          </Link>
        </Button>
      </Toolbar>
    </AppBar>
  )
}

export default HeaderSummarize
