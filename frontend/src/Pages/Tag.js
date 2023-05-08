import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import {Container, Typography} from "@mui/material";
import DisplayPost from "../Display/DisplayPost";

const Question = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [questions, setQuestions] = useState([])
    const {tag} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/questions/tag/${tag}`)
            .then(res => {
                setQuestions(res.data)
                setLoaded(true)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [tag])

    return loaded ? (
        <Container>
            <Typography variant={'h3'} sx={{p: 1}}>Questions with tag: {tag}</Typography>
            {questions.map(question => (<DisplayPost
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
    ) : ( <div>Loading</div>)
}

export default Question