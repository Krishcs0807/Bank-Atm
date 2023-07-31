const form =document.querySelector('#form')
const Acnumber =document.querySelector('#AcNumber')
const money =document.querySelector('#Money')
form.addEventListener('submit',(e)=>{
    if(!validateInputs()){
        e.preventDefault()
    }
})


function validateInputs(){
    const Acnumberval=Acnumber.value.trim();
    const Moneyval=money.value.trim();
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


    if(Moneyval===''){
        success=false;
        seterror(Money,'* Amount is required')
    }else if(Moneyval>=10000 || Moneyval<100 || Moneyval%100 != 0){
        success=false;
        seterror(money,'* minumum 100 and maximum 10000')
    }
    else{
        setsuccess(money)
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