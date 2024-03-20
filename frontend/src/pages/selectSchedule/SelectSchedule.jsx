import axios from 'axios'
import './selectSchedule.css'
import moment from 'moment/moment'
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate } from 'react-router-dom'

const convertToCustomDateFormat = (inputDate) => {
  let resultDate = ''
  const dateParts = inputDate.split('-')

  // const year = dateParts[0]
  const day = dateParts[2]
  const month = dateParts[1]
  resultDate = `${day}-${month}`
  return resultDate
}

const convertDate = (inputDate) => {
  let resultDate = ''
  const currentDate = new Date()
  const year = currentDate.getFullYear()
  const dateParts = inputDate.split('-')

  const month = dateParts[1]
  const day = dateParts[0]
  resultDate = `${year}-${month}-${day}`

  return resultDate
}

const SelectSchedule = () => {
  const idFilm = useLocation().pathname.split('/')[2]
  const [schedules, setSchedules] = useState([])
  const [filteredSchedules, setFilteredSchedules] = useState([])
  const [time, setTime] = useState(
    convertToCustomDateFormat(new Date().toISOString().slice(0, 10))
  )
  const [location, setLocation] = useState('Hà Nội')
  const [locations, setLocationS] = useState(['Hà Nội', 'Hồ Chí Minh'])

  const [dates, setDates] = useState([])

  const navigate = useNavigate()

  useEffect(() => {
    const fetchData = async () => {
      let res
      try {
        res = await axios.get(`/api/lichchieus/phim/${idFilm}`)
        setSchedules(res.data)
        console.log(res.data)
        // console.log(JSON.stringify(res.data))
      } catch (err) {
        console.log(err)
      }
    }
    fetchData()
  }, [idFilm])

  useEffect(() => {
    const currentDate = moment() // Ngày tháng hiện tại
    const next30Days = [] // Mảng chứa 30 ngày tiếp theo

    for (let i = 0; i < 30; i++) {
      const date = currentDate.clone().add(i, 'days')
      next30Days.push(date.format('DD-MM'))
    }

    setDates(next30Days)
  }, [time])

  // Hàm để lọc lịch chiếu dựa trên thời gian và địa điểm
  const filterSchedules = () => {
    return schedules.filter((schedule) => {
      const scheduleDate = schedule.date
      const scheduleLocation = schedule.phongChieu.rap.address

      return scheduleDate === convertDate(time) && scheduleLocation === location
    })
  }

  // Effect để log kết quả lọc ra các lịch chiếu khi có sự thay đổi trong time hoặc location
  useEffect(() => {
    const filter = filterSchedules()
    console.log(filter)
    setFilteredSchedules(filter)
  }, [time, location, schedules])

  const handleDateClick = (date) => {
    setTime(date) // Lưu trữ chỉ số của ngày được chọn
  }

  const handleLocationClick = (locationEle) => {
    setLocation(locationEle) // Lưu trữ chỉ số của ngày được chọn
  }

  const handleSelectSchedule = (schedule) => {
    navigate(`/schedules/${schedule.id}/tickets`)
  }

  return (
    <>
      <div className='container'>
        <div className='container-wrapper'>
          <div className='dates'>
            {dates.map((date, index) => (
              <div
                onClick={() => handleDateClick(date)}
                className={`date ${date === time ? 'selectedDate' : ''}`}
                key={index}
              >
                {date}
              </div>
            ))}
          </div>
          <div className='locations'>
            {locations.map((locationEle, index) => (
              <div
                onClick={() => handleLocationClick(locationEle)}
                className={`date ${
                  locationEle === location ? 'selectedDate' : ''
                }`}
                key={index}
              >
                {locationEle}
              </div>
            ))}
          </div>
          <div className='schedules'>
            {filteredSchedules.map((schedule, index) => (
              <div className='cinema' key={index}>
                <div className='cinemaName'>
                  {schedule.phongChieu.rap.name} - {schedule.phongChieu.name}
                </div>
                <div
                  className='time'
                  onClick={() => handleSelectSchedule(schedule)}
                >
                  {schedule.khungGio.timeStart}
                </div>
              </div>
            ))}
          </div>
        </div>
      </div>
      {/* </div> */}
    </>
  )
}

export default SelectSchedule
