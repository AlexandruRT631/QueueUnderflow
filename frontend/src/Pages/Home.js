import DisplayBar from "../Display/DisplayBar";
import {useEffect, useState} from "react";
import axios from "axios";
import {Container} from "@mui/material";
import DisplayPost from "../Display/DisplayPost";

const Home = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [questions, setQuestions] = useState([])

    useEffect(() => {
        axios.get(`http://localhost:8080/questions/getAll`)
            .then(res => {
                setQuestions(res.data)
                setLoaded(true)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    })

    return loaded ? (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            <Container>
                {questions
                    .map(question => (<DisplayPost
                        key={`q${question.id}`}
                        id={question.id}
                        theme={props.theme}
                        borderColor={`primary.dark`}
                        bgcolor={`secondary.light`}
                        title={question.title}
                        content={question.content}
                        href={`/questions/${question.id}`}
                    />))}
            </Container>
        </>
    ) : (<div>Loading</div>)
}

export default Home