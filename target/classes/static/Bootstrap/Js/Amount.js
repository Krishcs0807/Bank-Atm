const form =document.querySelector('#form')
const PIN =document.querySelector('#Money')
form.addEventListener('submit',(e)=>{
    if(!validateInputs()){
        e.preventDefault()
    }
})


function validateInputs(){
    const Moneyval=Money.value.trim();
    let success=true;
    if(Moneyval===''){
        success=false;
        seterror(Money,'* Amount is required')
    }else if(Moneyval>=10000 || Moneyval<100 || Moneyval%100 != 0){
        success=false;
        seterror(Money,'* minumum 100 and maximum 10000')
    }
    else{
        setsuccess(Money)
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