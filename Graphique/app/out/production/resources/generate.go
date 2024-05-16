package main

import (
	"fmt"
	"os"
	"slices"
	"strconv"

	rdg "github.com/TadaTeruki/RDGL"
	output "github.com/TadaTeruki/RDGL/Output"
)

func main() {
	args := os.Args[1:]
	seedN := 554540
	if len(args) > 0 {
		i, err := strconv.Atoi(args[0])
		if err != nil {
			// ... handle error
			panic(err)
		} else {
			seedN = i
		}
	}
	var seed int64 = int64(seedN)
	dem := rdg.NewDEM(seed)
	dem.LandProportion01 = 0.71
	dem.Generate()
	if slices.Contains(args, "png") {
		output.WriteDEMtoPNG("result.png", &dem, 300, -1)
	}

	//output.WriteDEMtoPNGwithShadow("result.png", &dem, 3000, -1, output.DefaultShadow(&dem))
	//output.WriteDEMtoTXT("result.txt", &dem)
	if slices.Contains(args, "ascii") {
		for yKm := 0.0; yKm < dem.HorizontalKm; yKm += dem.HorizontalKm / 50.0 {
			str := ""
			for xKm := 0.0; xKm < dem.VerticalKm; xKm += dem.HorizontalKm / 50.0 {

				elevation, _ := dem.GetElevationByKmPoint(xKm, yKm)

				if elevation >= 1300 {
					str += "█"
				} else if elevation >= 800 {
					str += "▓"
				} else if elevation >= 300 {
					str += "▒"
				} else if elevation >= 50 {
					str += "░"
				} else if elevation >= 0 {
					str += "~"
				} else {
					str += " "
				}

			}
			fmt.Println(str)
		}
	} else if slices.Contains(args, "emoji") {
		for yKm := 0.0; yKm < dem.HorizontalKm; yKm += dem.HorizontalKm / 50.0 {
			str := ""
			for xKm := 0.0; xKm < dem.VerticalKm; xKm += dem.HorizontalKm / 50.0 {

				elevation, _ := dem.GetElevationByKmPoint(xKm, yKm)

				if elevation >= 1300 {
					str += "⬜"
				} else if elevation >= 800 {
					str += "🟫"
				} else if elevation >= 300 {
					str += "🟩"
				} else if elevation >= 50 {
					str += "🟨"
				} else if elevation >= 0 {
					str += "🟪"
				} else {
					str += "🟦"
				}

			}
			fmt.Println(str)
		}
	} else {
		for yKm := 0.0; yKm < dem.HorizontalKm; yKm += dem.HorizontalKm / 50.0 {
			str := ""
			for xKm := 0.0; xKm < dem.VerticalKm; xKm += dem.HorizontalKm / 50.0 {

				elevation, _ := dem.GetElevationByKmPoint(xKm, yKm)

				if elevation >= 1300 {
					str += "P"
				} else if elevation >= 800 {
					str += "M"
				} else if elevation >= 300 {
					str += "J"
				} else if elevation >= 50 {
					str += "G"
				} else if elevation >= 0 {
					str += "D"
				} else {
					str += "A"
				}

			}
			fmt.Println(str)
		}
	}
	// Get max elevation
	maxElevation := 0.0
	for yKm := 0.0; yKm < dem.HorizontalKm; yKm += dem.HorizontalKm / 50.0 {
		for xKm := 0.0; xKm < dem.VerticalKm; xKm += dem.HorizontalKm / 50.0 {
			elevation, _ := dem.GetElevationByKmPoint(xKm, yKm)
			if elevation > maxElevation {
				maxElevation = elevation
			}
		}
	}
	// print(fmt.Sprintf("\nMax elevation: %.2f", maxElevation))

}
