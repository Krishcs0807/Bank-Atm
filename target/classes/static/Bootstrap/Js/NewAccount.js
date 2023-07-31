const form =document.querySelector('#form')
const Acnumber =document.querySelector('#accountNumber')
const Name =document.querySelector('#name')
const phoneNumber=document.querySelector('#phoneNumber')

form.addEventListener('submit',(e)=>{
    
    

    if(!validateInputs()){
        e.preventDefault()
    }
})


function validateInputs(){
    const Acnumberval=Acnumber.value.trim();
    const nameval=Name.value.trim();
    const phoneNumberval=phoneNumber.value.trim();
    let success=true;

    if(Acnumberval===''){
        success=false;
        seterror(Acnumber,'*AccountNumber is required*')
    }else if(!validateAcnumber(Acnumberval)){
        success=false;
        seterror(Acnumber,'*AccountNumber 11 digites only required'<br>'* Starting number 0 not allowed')
    }
    else{
        setsuccess(Acnumber)
    }


    if(nameval===''){
        success=false;
        seterror(Name,'*name is required*')
    }
    else{
        setsuccess(Name)
    }

    if(phoneNumberval===''){
        success=false;
        seterror(phoneNumber,"*Phone is required*")
    }
    else if(!validatephoneNumber(phoneNumberval)){
        success=false;
        seterror(phoneNumber,'*please enter valid Phonenumber*')
    }
    else{
        setsuccess(phoneNumber)
    }

    return success;

}

function seterror(element,message){
    const inputgroup=element.parentElement;
    const errorelement=inputgroup.querySelector('.error')

    errorelement.innerText=message;
    inputgroup.classList.add('error')
    inputgroup.classList.remove('success')
}
function setsuccess(element){
    const inputgroup=element.parentElement;
    const errorelement=inputgroup.querySelector('.error')

    errorelement.innerText='';
    inputgroup.classList.add('success')
    inputgroup.classList.remove('error')
}

const validateAcnumber = (Acnumber) => {
    return String(Acnumber)
    .match("^[1-9]{1}[0-9]{10}$");
};


const validatephoneNumber = (phoneNumber) => {
    return String(phoneNumber)
    .match("^[6-9]{1}[0-9]{9}$");
};