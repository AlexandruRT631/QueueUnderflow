import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import DisplayAnswer from "../Display/DisplayAnswer";
import {Container} from "@mui/material";
import DisplayQuestion from "../Display/DisplayQuestion";

const Question = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [question, setQuestion] = useState({
        id: 0,
        title: "defaultTitle",
        content: "defaultContent",
        userId: 0,
        userFirstName: "defaultUserFirstName",
        userLastName: "defaultUserLastName",
        userPicture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        date: "defaultDate",
        picture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        votes: [],
        answers: [],
        tags: []
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/questions/getById/${id}`)
            .then(res => {
                setQuestion(res.data)
                setLoaded(true)
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ? (
        <Container>
            <DisplayQuestion theme={props.theme}
                             title={question.title}
                             content={question.content}
                             picture={question.picture}
                             tags={question.tags}
                             date={question.date}
                             vote={question.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}
                             userId={question.userId}
                             userPicture={question.userPicture}
                             userFirstName={question.userFirstName}
                             userLastName={question.userLastName}
            />
            {question.answers.map(answer => (<DisplayAnswer key={answer.id}
                                                            theme={props.theme}
                                                            userId={answer.userId}
                                                            userPicture={answer.userPicture}
                                                            userFirstName={answer.userFirstName}
                                                            userLastName={answer.userLastName}
                                                            votes={answer.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}
                                                            content={answer.content}
                                                            picture={answer.picture}
                                                            date={answer.date}
            />))}
        </Container>
    ) : ( <div>Loading</div>)
}

export default Question