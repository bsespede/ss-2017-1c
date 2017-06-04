function ej5multi(fileName, plotTitle, plotXLabel, plotYLabel, imagePath) 

  [key value] =  textread(cstrcat("../../results/1,2,3/1-", fileName, ".dat"), "%f %f");
  plot(key, value, 'g');
  hold on;
  
  [key value] =  textread(cstrcat("../../results/1,2,3/2-", fileName, ".dat"), "%f %f");
  plot(key, value, 'b');
  hold on;
  
  [key value] =  textread(cstrcat("../../results/1,2,3/3-", fileName, ".dat"), "%f %f");
  plot(key, value, 'r');
  hold on;
  
  [key value] =  textread(cstrcat("../../results/1,2,3/4-", fileName, ".dat"), "%f %f");
  plot(key, value, 'c');
  hold on;
  
  [key value] =  textread(cstrcat("../../results/1,2,3/5-", fileName, ".dat"), "%f %f");
  plot(key, value, 'm');
  hold on;
  
  title(plotTitle);
  xlabel(plotXLabel);
  ylabel(plotYLabel);
  print(imagePath);
  legend('Corrida 1', 'Corrida 2', 'Corrida 3', 'Corrida 4', 'Corrida 5');
  
endfunction