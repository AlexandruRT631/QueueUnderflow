import DisplayBar from "../Display/DisplayBar";
import React, {useEffect, useState} from "react";
import axios from "axios";
import {Button, Container, ThemeProvider} from "@mui/material";
import DisplayPost from "../Display/DisplayPost";
import {useNavigate} from "react-router-dom";
import InfiniteScroll from "react-infinite-scroll-component";

const Home = (props) => {
    const navigate = useNavigate();

    const [loaded, setLoaded] = useState(false)
    const [questions, setQuestions] = useState([])
    const [page, setPage] = useState(1)
    const [hasMore, setHasMore] = useState(true)

    const fetchData = () => {
        axios.get(`http://localhost:8080/questions/getAll?page=${page}`)
            .then(res => {
                setQuestions([...questions, ...res.data])
                if (res.data.length < 10) {
                    setHasMore(false)
                }
                setPage(page + 1)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/questions/getAll?page=${page}`)
            .then(res => {
                setQuestions([...questions, ...res.data])
                if (res.data.length < 10) {
                    setHasMore(false)
                }
                setPage(page + 1)
                setLoaded(true)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    })

    return loaded ? (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            {props.token && <ThemeProvider theme={props.theme}>
                <Button variant="contained" onClick={() => {
                    navigate('/newQuestion')
                }} sx={{m: 1, alignSelf: 'center'}}>New Question</Button>
            </ThemeProvider>}
            <Container>
                <InfiniteScroll
                    dataLength={questions.length}
                    next={fetchData}
                    hasMore={hasMore} // Replace with a condition based on your data source
                    loader={<p>Loading...</p>}
                >
                    <ul style={{ listStyleType: 'none' }}>
                        {questions
                            .map(question => (<li key={question.id}><DisplayPost
                                key={`q${question.id}`}
                                id={question.id}
                                theme={props.theme}
                                borderColor={`primary.dark`}
                                bgcolor={`secondary.light`}
                                title={question.title}
                                content={question.content}
                                href={`/questions/${question.id}`}
                            /></li>))}
                    </ul>
                </InfiniteScroll>
            </Container>
        </>
    ) : (<div>Loading</div>)
}

export default Home