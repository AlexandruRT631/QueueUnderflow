import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import DisplayAnswer from "../Display/DisplayAnswer";
import DisplayBar from "../Display/DisplayBar";

const Answer = (props) => {
    const [loaded, setLoaded] = useState(false)
    const [answer, setAnswer] = useState({
        userFirstName: "defaultUserFirstName",
        userLastName: "defaultUserLastName",
        userPicture: "none",
        questionId: 0,
        questionTitle: "defaultQuestionTitle",
        content: "defaultContent",
        date: "defaultDate",
        picture: "none",
        votes: [
            {
                userId: 0,
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
                // console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ? (
        <>
            <DisplayBar theme={props.theme} token={props.token}/>
            <DisplayAnswer userPicture={answer.userPicture}
                           userFirstName={answer.userFirstName}
                           userLastName={answer.userLastName}
                           votes={answer.votes.map(value => value.positiveVote).reduce((acc, cur) => acc + (cur ? 1 : -1), 0)}
                           content={answer.content}
                           picture={answer.picture}
                           date={answer.date}
            ></DisplayAnswer>
        </>
    ) : ( <div>Loading</div>)
}

export default Answer