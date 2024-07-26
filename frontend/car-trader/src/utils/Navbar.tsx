import Cookies from 'universal-cookie';

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
                            <li><a href="/logout" >Logout</a></li>
                        </ul>
                    </nav>
                    
                </>
            
            )
    }
    
}