import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";
import DisplayPost from "./DisplayPost";

const Question = () => {
    const [loaded, setLoaded] = useState(false)
    const [question, setQuestion] = useState({
        title: "defaultTitle",
        content: "defaultContent",
        userFirstName: "defaultUserFirstName",
        userLastName: "defaultUserLastName",
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
                console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ? (
        <div>
            <p>Question title: {question.title}</p>
            <p>Question content: {question.content}</p>
            <p>Question user first name: {question.userFirstName}</p>
            <p>Question user last name: {question.userLastName}</p>
            <p>Question date: {question.date}</p>
            <img src={question.picture} alt={"unavailable"}/>
            {question.answers.map(answer => (<DisplayPost title={answer.title} body={answer.content}/>))}
        </div>
    ) : ( <div>Loading</div>)
}

export default Question