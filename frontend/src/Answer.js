import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

const Answer = () => {
    const [loaded, setLoaded] = useState(false)
    const [answer, setAnswer] = useState({
        userFirstName: "defaultUserFirstName",
        userLastName: "defaultUserLastName",
        questionTitle: "defaultQuestionTitle",
        content: "defaultContent",
        date: "defaultDate",
        picture: "none",
        votes: [
            {
                userFirstName: "defaultUserFirstName",
                userLastName: "defaultUserLastName",
                positiveVote: true
            }
        ]
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/answers/getById/${id}`)
            .then(res => {
                setAnswer(res.data)
                setLoaded(true)
                console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ? (
        <div>
            <p>Question title: {answer.questionTitle}</p>
            <p>Question content: {answer.content}</p>
            <p>Question user first name: {answer.userFirstName}</p>
            <p>Question user last name: {answer.userLastName}</p>
            <p>Question date: {answer.date}</p>
            <img src={answer.picture} alt={"unavailable"}/>
            <p>Vote: {answer.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}</p>
        </div>
    ) : ( <div>Loading</div>)
}

export default Answer