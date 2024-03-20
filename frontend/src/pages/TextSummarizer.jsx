import React, { useContext, useState } from 'react'
import {
  Button,
  TextareaAutosize,
  Typography,
  Container,
  Grid,
  Box,
  Select,
  MenuItem,
} from '@mui/material'
import HeaderSummarize from './HeaderSummarize'
import { AuthContext } from '../context/AuthContext'
import axios from 'axios'

const TextSummarizer = () => {
  const [inputText, setInputText] = useState('')
  const [summary, setSummary] = useState('')
  const [selectedLanguage, setSelectedLanguage] = useState('vi')

  const { user, dispatch } = useContext(AuthContext)

  const handleSummarize = async (event) => {
    event.preventDefault()
    try {
      const data = { text: inputText, user: user }
      const res = await axios.post(
        `/api/summarys?language=${selectedLanguage}`,
        data
      )

      // user.point -= total
      console.log(res.data)
      setSummary(res.data.summary)
    } catch (error) {
      console.log(error)
    }
  }

  const handleLanguageChange = (event) => {
    setSelectedLanguage(event.target.value)
    // Thực hiện các hành động khác liên quan đến thay đổi ngôn ngữ ở đây (nếu có)
  }

  return (
    <>
      <HeaderSummarize />

      <Container maxWidth='md'>
        <Box mt={5}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <Typography variant='h4' gutterBottom>
                Chức năng Tóm tắt Văn bản
              </Typography>
              <Grid
                item
                xs={3}
                style={{ height: '30px', marginBottom: '50px' }}
              >
                <Select
                  value={selectedLanguage}
                  onChange={handleLanguageChange}
                  style={{ width: '100%', fontSize: '16px', padding: '10px' }}
                >
                  <MenuItem value='en'>English</MenuItem>
                  <MenuItem value='vi'>Vietnamese</MenuItem>
                </Select>
              </Grid>
              <TextareaAutosize
                minRows={8} // Số hàng tối thiểu
                placeholder='Nhập văn bản cần tóm tắt'
                value={inputText}
                onChange={(e) => setInputText(e.target.value)}
                style={{
                  width: '100%',
                  fontSize: '16px',
                  padding: '10px',
                  marginTop: '20px',
                }} // Tùy chỉnh kích thước và font chữ
              />
            </Grid>
            <Grid item xs={12}>
              <Button
                variant='contained'
                onClick={(event) => handleSummarize(event)}
              >
                Tóm tắt
              </Button>
            </Grid>
            {summary && (
              <Grid item xs={12}>
                <Typography variant='h6' gutterBottom>
                  Kết quả tóm tắt:
                </Typography>
                <Typography>{summary}</Typography>
              </Grid>
            )}
          </Grid>
        </Box>
      </Container>
    </>
  )
}

export default TextSummarizer
