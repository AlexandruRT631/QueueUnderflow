import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import axios from "axios";
import DisplayBar from "../Display/DisplayBar";
import {Container, Typography} from "@mui/material";
import DisplayPost from "../Display/DisplayPost";
import InfiniteScroll from "react-infinite-scroll-component";

const Search = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [questions, setQuestions] = useState([])
    const [page, setPage] = useState(1)
    const [hasMore, setHasMore] = useState(true)
    const {search} = useParams()

    const fetchData = () => {
        axios.get(`http://localhost:8080/questions/search/${search}?page=${page}`)
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
        axios.get(`http://localhost:8080/questions/search/${search}?page=${page}`)
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
            <Container>
                <Typography variant={'h3'} sx={{p: 1}}>Questions with Title: {search}</Typography>
                <InfiniteScroll
                    dataLength={questions.length}
                    next={fetchData}
                    hasMore={hasMore} // Replace with a condition based on your data source
                    loader={<p>Loading...</p>}
                >
                    <ul style={{listStyleType: 'none'}}>
                        {questions.map(question => (<li key={question.id}><DisplayPost
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

export default Search