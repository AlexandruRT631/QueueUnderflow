import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

const User = () => {
    const [loaded, setLoaded] = useState(false)
    const [user, setUser] = useState({
        firstName: "defaultFirstName",
        lastName: "defaultSecondName",
        picture: "https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png",
        moderator: false,
        banned: false
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/users/getById/${id}`)
            .then(res => {
                setUser(res.data)
                setLoaded(true)
                console.log(res.data)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ?
        (
            <div>
                <p>First Name: {user.firstName}</p>
                <p>Last Name: {user.lastName}</p>
                <img src={user.picture} alt={"unavailable"} width={200} height={200}/>
                <p>Moderator: {user.moderator ? "True" : "False"}</p>
                <p>Banned: {user.banned ? "True" : "False"}</p>
            </div>
        ) : ( <div>Loading</div>)
}

export default User