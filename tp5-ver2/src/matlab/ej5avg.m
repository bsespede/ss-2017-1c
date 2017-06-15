function ej5avg(fileName, numFiles, plotTitle, plotXLabel, plotYLabel, imagePath) 

  columnSize = zeros(numFiles, 1);
  for fileNumber = 1:1:numFiles
    [key value] =  textread(cstrcat("../../results/1,2,3/",  num2str(fileNumber), "-", fileName, ".dat"), "%f %f");
    columnSize(fileNumber) = size(key)(1,1);
  endfor
  lastColumn = min(columnSize)
  
  x = 1:1:lastColumn;
  avgY = 1:1:lastColumn;
  minY = 1:1:lastColumn;
  maxY = 1:1:lastColumn;
  
  for index = 1:1:lastColumn
    allValues = zeros(numFiles, 1);
    for fileNumber = 1:1:numFiles
      [key value] =  textread(cstrcat("../../results/1,2,3/",  num2str(fileNumber), "-", fileName, ".dat"), "%f %f");
      value(index);
      allValues(fileNumber) = value(index);
    endfor
    avgY(index) = mean(allValues);
    minY(index) = min(allValues);
    maxY(index) = max(allValues);
    x(index) = key(index);
  endfor

  errorbar(x, avgY, maxY - minY, ".");
  title(plotTitle);
  xlabel(plotXLabel);
  ylabel(plotYLabel);
  print(imagePath);
  
endfunction