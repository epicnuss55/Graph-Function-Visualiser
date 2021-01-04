let canvas = document.getElementById("Display");
let label1 = document.getElementById("L1");

let ctx = canvas.getContext("2d");
let midX = canvas.width / 2;
let midY = canvas.height /2;
let max = 100;


function Calculate(value) {
    if(value.includes("^")) value = value.replace("^", "**");
    if(value.includes("sin(")) value = value.replace("sin(", "Math.sin(");
    if(value.includes("cos(")) value = value.replace("cos(", "Math.cos(");
    if(value.includes("log2(")) value = value.replace("log2(", "Math.log2(");
    if(value.includes("log10(")) value = value.replace("log10(","Math.log10(");
    
    gridInit(value);
}


function gridInit(value) {

    ctx.clearRect(0,0, canvas.width, canvas.height);



    ctx.beginPath();
    ctx.strokeStyle = "#B1B1B1";
    ctx.lineWidth = 1;
    
    for(let x = -max; x < max; x++) {
        if(x == 0) continue;
        
        ctx.moveTo(midX + x * 40, -max * 40 + midY);
        ctx.lineTo(midX + x * 40, max * 40 + midY);
        ctx.stroke();
    }
    
    for(let y = -max; y < max; y++) {
        if (y == 0) continue;
        
        ctx.moveTo(-max * 40 + midX, midY + y * 40);
        ctx.lineTo(max * 40 + midX, midY + y * 40);
        ctx.stroke();
    }
    


    ctx.beginPath();
    ctx.strokeStyle = "#FF0000";
    ctx.lineWidth = 3;
    
    ctx.moveTo(midX, midY + -max * 40);
    ctx.lineTo(midX, midY + max * 40);
    ctx.stroke();
    
    ctx.moveTo(midX + -max * 40, midY);
    ctx.lineTo(midX + max * 40, midY);
    ctx.stroke();
     


    ctx.beginPath();
    ctx.strokeStyle = "#00AEFF";
    ctx.lineWidth = 1;
    
    for (let x = -max; x < max; x = x+=0.1) {
        ctx.moveTo(midX + x * 40, midY + -eval(value) * 40);
        
        let x2 = x + 0.1;
        let val2 = value.replace("x", "x2");
        ctx.lineTo(midX + x2 * 40, midY + -eval(val2) * 40);
        ctx.stroke();
    }
}

label1.onchange = function() {
    let Value = label1.value.toLowerCase();
    Calculate(Value);
}