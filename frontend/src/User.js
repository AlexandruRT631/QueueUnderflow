import {useEffect, useState} from "react";
import axios from "axios";
import {useParams} from "react-router-dom";

const User = () => {
    const [loaded, setLoaded] = useState(false)
    const [user, setUser] = useState({
        firstName: "defaultFirstName",
        lastName: "defaultSecondName",
    })
    const {id} = useParams()

    useEffect(() => {
        axios.get(`http://localhost:8080/users/getById/${id}`)
            .then(res => {
                setUser(res.data)
                setLoaded(true)
            })
            .catch(err => console.log(err))
    }, [id])

    return loaded ?
        (
            <div>
                <p>First Name: {user.firstName}</p>
                <p>Last Name: {user.lastName}</p>
            </div>
        ) : ( <div>Loading</div>)
}

export default User