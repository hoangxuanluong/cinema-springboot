import React, { useContext, useEffect, useState } from 'react'
import { List, ListItem, ListItemText, Typography } from '@mui/material'
import HeaderSummarize from './HeaderSummarize'
import axios from 'axios'
import { AuthContext } from '../context/AuthContext'

// Dữ liệu giả mạo cho lịch sử tóm tắt
const historyData = [
  {
    id: 1,
    title: 'Sự ra đời của React',
    content:
      'Theo ông Nguyễn Đắc Luân, Phó Chủ tịch Hiệp hội Bưu chính, với việc nở rộ gần 800 doanh nghiệp bưu chính đang hoạt động ở Việt Nam hiện nay, trong đó có các doanh nghiệp nước ngoài đầu tư đang chiếm thị phần lớn, đã tạo nên cuộc cạnh tranh khốc liệt của thị trường dẫn đến việc giảm giá xảy ra thường xuyên, các chương trình khuyến mại với giá giảm rất sâu, có cả hiện tượng giảm trọng lượng để giảm giá bán. Đồng thời, pháp luật chưa quy định khung giá thấp nhất cho hoạt động bưu chính, trong đó có hoạt động chuyển phát TMĐT. Giá cước dịch vụ bưu chính trên thị trường của một số doanh nghiệp có yếu tố nước ngoài thấp hơn giá thành của các doanh nghiệp bưu chính có mạng lưới lớn trong nước. Việc mất cân bằng về giá dịch vụ giữa doanh nghiệp bưu chính trong nước và nước ngoài gây ra không ít hệ lụy, trong đó có thể kể đến nguy cơ thị phần bưu chính trong nước sẽ bị thâu tóm. Sự bắt tay của các sàn TMĐT và các hãng chuyển phát nước ngoài có thể dẫn đến nguy cơ thị trường bị thống lĩnh cả về mặt hàng hóa lẫn dịch vụ hậu cần.',
    summary:
      'Việc mất cân bằng về giá dịch vụ giữa doanh nghiệp bưu chính trong nước và nước ngoài gây ra không ít hệ lụy, trong đó có thể kể đến nguy cơ thị phần bưu chính trong nước sẽ bị thâu tóm.',
  },
]

// Style CSS cho component
const listItemStyle = {
  marginBottom: '16px',
  boxShadow: '0 2px 4px rgba(0, 0, 0, 0.1)',
  borderRadius: '4px',
  '&:hover': {
    backgroundColor: '#f5f5f5',
  },
}

// Component cho mỗi mục lịch sử
const HistoryItem = ({ title, content, summary }) => {
  return (
    <ListItem sx={listItemStyle}>
      <ListItemText
        primary={
          <>
            {/* <Typography variant='h6'>{title}</Typography> */}
            <Typography variant='body1'>{content}</Typography>
          </>
        }
        secondary={
          <Typography
            variant='body2'
            color='textSecondary'
            style={{ marginTop: '10px' }}
          >
            {summary}
          </Typography>
        }
      />
    </ListItem>
  )
}

// Component chính hiển thị danh sách lịch sử tóm tắt
const HistoryList = () => {
  const [summaries, setSummaries] = useState([])
  const { user, dispatch } = useContext(AuthContext)

  useEffect(() => {
    const fetchData = async () => {
      let res
      try {
        res = await axios.get(`/api/summarys/users/${user.id}/summaries`)
        console.log(res.data)
        setSummaries(res.data)
      } catch (err) {
        console.log(err)
      }
    }
    fetchData()
  }, [])
  return (
    <>
      <HeaderSummarize />
      <List style={{ marginTop: '50px' }}>
        {summaries.map((item) => (
          <HistoryItem
            key={item.id}
            content={item.text}
            summary={item.summary}
          />
        ))}
      </List>
    </>
  )
}

export default HistoryList
