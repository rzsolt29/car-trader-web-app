import Cookies from 'universal-cookie';
import { useNavigate } from "react-router-dom";
import { Button } from '../components/Button';

const cookies = new Cookies();

export const Navbar = () => {

    if(cookies.get("Authorization") === undefined) {
        return (
            <>
                <a className="brand" href="/" >Home</a>   
                    <nav>
                        <ul>
                            <li><a href="/login" >Sign In</a></li>
                        </ul>
                    </nav>
            </>
            )
    }else{


        return (
            <>
            <a className="brand" href="/" >Home</a>   
                    <nav>
                        <ul>
                            <li><a href="/profile" >Profile</a></li>
                            <li>
                                <Button handleClick={(event) => {
                                    console.log('Button clicked', event)
                                }}
                                />
                            </li>
                        </ul>
                    </nav>
                    
                </>
            
            )
    }

    
}