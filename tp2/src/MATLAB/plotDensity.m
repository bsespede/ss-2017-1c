function plotDensity(input, output)

    data = dlmread(input);
    [maxX, maxY] = size(data);

    colormap(jet);
    imagesc([0, maxX], [0, maxY], data);
    colorbar;
    axis xy;
    
    print(output, '-dpng');
    
end

